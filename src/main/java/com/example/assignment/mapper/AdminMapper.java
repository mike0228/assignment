package com.example.assignment.mapper;

import com.example.assignment.model.Admin;
import com.example.assignment.model.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMIN
     *
     * @mbg.generated Sun Sep 15 17:44:15 CST 2019
     */
    long countByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMIN
     *
     * @mbg.generated Sun Sep 15 17:44:15 CST 2019
     */
    int deleteByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMIN
     *
     * @mbg.generated Sun Sep 15 17:44:15 CST 2019
     */
    int insert(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMIN
     *
     * @mbg.generated Sun Sep 15 17:44:15 CST 2019
     */
    int insertSelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMIN
     *
     * @mbg.generated Sun Sep 15 17:44:15 CST 2019
     */
    List<Admin> selectByExampleWithRowbounds(AdminExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMIN
     *
     * @mbg.generated Sun Sep 15 17:44:15 CST 2019
     */
    List<Admin> selectByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMIN
     *
     * @mbg.generated Sun Sep 15 17:44:15 CST 2019
     */
    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMIN
     *
     * @mbg.generated Sun Sep 15 17:44:15 CST 2019
     */
    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);
}