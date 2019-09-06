package com.example.assignment.service;

import com.example.assignment.dto.PaginationDTO;
import com.example.assignment.dto.PostInDTO;
import com.example.assignment.mapper.PostInMapper;
import com.example.assignment.mapper.UserMapper;
import com.example.assignment.model.PostIn;
import com.example.assignment.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostInService {
    @Autowired
    private PostInMapper postInMapper;
    @Autowired
    private UserMapper userMapper;
    public PaginationDTO list(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<PostIn> postIns = postInMapper.list(offset , size);
        List<PostInDTO> postInDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for(PostIn postIn : postIns)
        {
            User user = userMapper.searchById(postIn.getCreator());
            PostInDTO postInDTO = new PostInDTO();
            BeanUtils.copyProperties(postIn, postInDTO);
            postInDTO.setUser(user);
            postInDTOList.add(postInDTO);
        }
        paginationDTO.setPostIn(postInDTOList);
        Integer totalCount = postInMapper.count();
        paginationDTO.setPagination(totalCount,page,size);
        return paginationDTO;
    }
}
