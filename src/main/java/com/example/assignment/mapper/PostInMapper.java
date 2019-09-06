package com.example.assignment.mapper;

import com.example.assignment.model.PostIn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostInMapper {
    @Insert("insert into postIn (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void create(PostIn postIn);

    @Select("select * from postIn")
    List<PostIn> list();
}
