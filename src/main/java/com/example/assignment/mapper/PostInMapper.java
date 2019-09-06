package com.example.assignment.mapper;

import com.example.assignment.model.PostIn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostInMapper {
<<<<<<< HEAD
    @Insert("insert into postin (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void create(PostIn postIn);

    @Select("select * from postin limit #{offset},#{size}")
    List<PostIn> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from postin")
    Integer count();
=======
    @Insert("insert into postIn (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void create(PostIn postIn);

    @Select("select * from postIn")
    List<PostIn> list();
>>>>>>> 4ab3b5e353868e201a26a5ea0ed74c85144d5d83
}
