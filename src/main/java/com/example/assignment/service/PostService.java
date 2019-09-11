package com.example.assignment.service;

import com.example.assignment.dto.PaginationDTO;
import com.example.assignment.dto.PostDTO;
import com.example.assignment.dto.PostQueryDTO;
import com.example.assignment.exception.CustomizeErrorCode;
import com.example.assignment.exception.CustomizeException;
import com.example.assignment.mapper.PostExtMapper;
import com.example.assignment.mapper.PostMapper;
import com.example.assignment.mapper.UserMapper;
import com.example.assignment.model.Post;
import com.example.assignment.model.PostExample;
import com.example.assignment.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostExtMapper postExtMapper;

    public PaginationDTO list(String search,Integer page, Integer size) {
        if(StringUtils.isNotBlank(search)){
            String[] tags = StringUtils.split(search, " ");
            search  = Arrays.stream(tags).collect(Collectors.joining("|"));
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;

        PostQueryDTO postQueryDTO = new PostQueryDTO();
        postQueryDTO.setSearch(search);
        Integer totalCount = postExtMapper.countBySearch(postQueryDTO);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }


        if (page < 1)
            page = 1;

        if (page > totalPage)
            page = totalPage;
        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        PostExample postExample = new PostExample();
        postExample.setOrderByClause("gmt_create desc");
        postQueryDTO.setSize(size);
        postQueryDTO.setPage(offset);
        List<Post> posts = postExtMapper.selectBySearch(postQueryDTO);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (Post post : posts) {
            User user = userMapper.selectByPrimaryKey(post.getCreator());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOList.add(postDTO);
        }
        paginationDTO.setData(postDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        PostExample postExample = new PostExample();
        postExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) postMapper.countByExample(postExample);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1)
            page = 1;
        if (page > totalPage)
            page = totalPage;

        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        PostExample example = new PostExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Post> posts = postMapper.selectByExampleWithRowbounds(example,new RowBounds(offset, size));
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : posts) {
            User user = userMapper.selectByPrimaryKey(post.getCreator());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOList.add(postDTO);
        }
        paginationDTO.setData(postDTOList);
        return paginationDTO;
    }

    public PostDTO getById(Long id) {
        Post post = postMapper.selectByPrimaryKey(id);
        if(post==null){
            throw new CustomizeException(CustomizeErrorCode.POST_NOT_FOUND);
        }
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        User user = userMapper.selectByPrimaryKey(post.getCreator());
        postDTO.setUser(user);
        return postDTO;
    }

    public void createOrUpdate(Post post) {
        if (post.getId() == null){
            post.setGmtCreate(System.currentTimeMillis());
            post.setGmtModified(post.getGmtCreate());
            post.setViewCount(0);
            post.setLikeCount(0);
            post.setCommentCount(0);
            postMapper.insert(post);
        }else {
            post.setGmtModified(post.getGmtCreate());
            Post updatePost = new Post();
            updatePost.setGmtModified(System.currentTimeMillis());
            updatePost.setTitle(post.getTitle());
            updatePost.setDescription(post.getDescription());
            updatePost.setTag(post.getTag());
            PostExample example = new PostExample();
            example.createCriteria().andIdEqualTo(post.getId());
            int update = postMapper.updateByExampleSelective(updatePost, example);
            if(update != 1){
                throw new CustomizeException(CustomizeErrorCode.POST_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Post post =new Post();
        post.setId(id);
        post.setViewCount(1);
        postExtMapper.incView(post);
    }

    public List<PostDTO> selectRelated(PostDTO queryDTO) {
        if(StringUtils.isBlank(queryDTO.getTag())){
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Post post = new Post();
        post.setId(queryDTO.getId());
        post.setTag(regexpTag);
        List<Post> posts = postExtMapper.selectRelated(post);
        List<PostDTO> postDTOS = posts.stream().map(q->{
            PostDTO postDTO= new PostDTO();
            BeanUtils.copyProperties(q,postDTO);
            return postDTO;
        }).collect(Collectors.toList());
        return postDTOS;
    }

    public List<PostDTO> listHotTopics() {
        List<Post> hotTopics = postExtMapper.selectTopTen();
        List<PostDTO> hotTopicDTOs = hotTopics.stream().map(q->{
            PostDTO postDTO= new PostDTO();
            User user = userMapper.selectByPrimaryKey(q.getCreator());
            postDTO.setUser(user);
            BeanUtils.copyProperties(q,postDTO);
            return postDTO;
        }).collect(Collectors.toList());
        return hotTopicDTOs;
    }
}
