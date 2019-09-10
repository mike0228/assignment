package com.example.assignment.mapper;

import com.example.assignment.model.Post;
import com.example.assignment.model.PostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PostMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    long countByExample(PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    int deleteByExample(PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    int insert(Post record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    int insertSelective(Post record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    List<Post> selectByExampleWithBLOBsWithRowbounds(PostExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    List<Post> selectByExampleWithBLOBs(PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    List<Post> selectByExampleWithRowbounds(PostExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    List<Post> selectByExample(PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    Post selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    int updateByExampleSelective(@Param("record") Post record, @Param("example") PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") Post record, @Param("example") PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    int updateByExample(@Param("record") Post record, @Param("example") PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    int updateByPrimaryKeySelective(Post record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(Post record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POSTS
     *
     * @mbg.generated Wed Sep 11 04:38:32 CST 2019
     */
    int updateByPrimaryKey(Post record);
}