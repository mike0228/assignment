package com.example.assignment.mapper;

import com.example.assignment.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("select * from posts where id = #{id}")
    Post getById(@Param("id") Integer id);

    @Insert("insert into posts (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void create(Post post);

    @Select("select * from posts limit #{offset},#{size}")
    List<Post> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from posts")
    Integer count();

    @Select("select * from posts where creator = #{userId} limit #{offset},#{size}")
    List<Post> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from posts where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);

}
