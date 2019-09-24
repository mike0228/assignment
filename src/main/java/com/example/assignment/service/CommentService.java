package com.example.assignment.service;

import com.example.assignment.dto.CommentDTO;
import com.example.assignment.dto.CommentListDTO;
import com.example.assignment.enums.CommentTypeEnum;
import com.example.assignment.enums.NotificationStatusEnum;
import com.example.assignment.enums.NotificationTypeEnum;
import com.example.assignment.exception.CustomizeErrorCode;
import com.example.assignment.exception.CustomizeException;
import com.example.assignment.mapper.*;
import com.example.assignment.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostExtMapper postExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    @Transactional
    public void insert(Comment comment, User commentator) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            Post post = postMapper.selectByPrimaryKey(dbComment.getParentId());
            if (post == null) {
                throw new CustomizeException(CustomizeErrorCode.POST_NOT_FOUND);
            }
            commentMapper.insert(comment);
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentExtMapper.incCommentCount(parentComment);
            creatNotify(comment, dbComment.getCommentator(), commentator.getName(), post.getTitle(), NotificationTypeEnum.REPLY_COMMENT, post.getId());

        } else {
            Post post = postMapper.selectByPrimaryKey(comment.getParentId());
            if (post == null) {
                throw new CustomizeException(CustomizeErrorCode.POST_NOT_FOUND);
            }
            comment.setCommentCount(0);
            commentMapper.insert(comment);
            post.setCommentCount(1);
            postExtMapper.incCommentCount(post);
            creatNotify(comment, post.getCreator(), commentator.getName(), post.getTitle(), NotificationTypeEnum.REPLY_POST, post.getId());
        }
    }

    private void creatNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Long outerId) {
        if (receiver == comment.getCommentator()) {
            return;
        }
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create asc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList();
        userIds.addAll(commentators);
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            User user = userMap.get(comment.getCommentator());
            user.setToken("");
            commentDTO.setUser(user);
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }

    @Transactional
    public void incLikeCount(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
        } else {
            Post post = postMapper.selectByPrimaryKey(comment.getParentId());
            if (post == null) {
                throw new CustomizeException(CustomizeErrorCode.POST_NOT_FOUND);
            }
            commentExtMapper.incLikeCount(comment);
        }
    }

    public Comment getCommentById(Long id) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        if (comment == null) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
        }
        return comment;
    }

    public void decLikeCount(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
        } else {
            Post post = postMapper.selectByPrimaryKey(comment.getParentId());
            if (post == null) {
                throw new CustomizeException(CustomizeErrorCode.POST_NOT_FOUND);
            }
            commentExtMapper.decLikeCount(comment);
        }

    }

    public void addLike(Long commentId, Long userId) {
        Like like = new Like();
        like.setCommentId(commentId);
        like.setUserId(userId);
        likeMapper.insert(like);
    }

    public boolean isLiked(Long commentId, Long userId) {
        LikeExample likeExample = new LikeExample();
        LikeExample.Criteria criteria = likeExample.createCriteria();
        criteria.andCommentIdEqualTo(commentId);
        criteria.andUserIdEqualTo(userId);
        List<?> list = likeMapper.selectByExample(likeExample);
        if (list.size() == 0)
            return false;
        else
            return true;
    }

    public void deleteLike(Long commentId, Long userId) {
        LikeExample likeExample = new LikeExample();
        LikeExample.Criteria criteria = likeExample.createCriteria();
        criteria.andCommentIdEqualTo(commentId).andUserIdEqualTo(userId);
        likeMapper.deleteByExample(likeExample);
    }
    public CommentListDTO list(Long id,CommentTypeEnum type, Integer page, Integer size) {
        CommentListDTO commentListDTO = new CommentListDTO();
        Integer totalPage;
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create asc");
        Integer totalCount = (int) commentMapper.countByExample(commentExample);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1)
            page = 1;
        if (page > totalPage)
            page = totalPage;

        commentListDTO.setCommentListDTO(totalPage, page);
        Integer offset = size * (page - 1);
        CommentExample example = new CommentExample();
        example.createCriteria().andTypeEqualTo(type.getType()).andParentIdEqualTo(id);
        List<Comment> comments = commentMapper.selectByExampleWithRowbounds(example,new RowBounds(offset, size));
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : comments) {
            User user = userMapper.selectByPrimaryKey(comment.getCommentator());
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(user);
            commentDTOList.add(commentDTO);
        }
        commentListDTO.setData(commentDTOList);
        return commentListDTO;
    }

    public void deleteById(Long id, Boolean isAdministrator ,long postId) {
        if (isAdministrator != true) {
            throw new CustomizeException(CustomizeErrorCode.CANNOT_DELETE);
        }
        commentMapper.deleteByPrimaryKey(id);
        Post post =new Post();
        post.setId(postId);
        post.setCommentCount(1);
        postExtMapper.decCommentCount(post);
    }
}

