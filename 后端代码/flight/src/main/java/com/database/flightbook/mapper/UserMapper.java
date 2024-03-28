package com.database.flightbook.mapper;

import com.database.flightbook.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper

@Repository
public interface UserMapper {

    @Select("Select * from user")
    List<User> findall();

    @Select("Select * from user where phone = #{phone} and password = #{pass}")
    User login(@Param("phone") String phone, @Param("pass") String pass);

    @Select("Select * from user where phone = #{phone}")
    User isRegiste(@Param("phone") String phone);

    @Insert("Insert  Into user (`username`,`trueName`,`identityNumber`,`phone`,`mail`,`sex`,`type`,`password`) VALUES (#{username},#{trueName},#{identityNumber},#{phone},#{mail},#{sex},#{type},#{password})")
    int registe(@Param("username") String username, @Param("trueName") String trueName, @Param("identityNumber") String identityNumber, @Param("phone") String phone, @Param("mail") String mail, @Param("sex") String sex, @Param("type") String type, @Param("password") String password);

    @Select("Select phone from user where identityNumber = #{identityNumber}")
    String readyForget(@Param("identityNumber") String identityNumber);

    @Update("Update user SET password = #{password} WHERE identityNumber=#{identityNumber}")
    int forget(@Param("identityNumber") String identityNumber, @Param("password") String password);

    @Select("Select * from user ")
    List<User> getAllUsers();

    @Select("Select userId from user where phone = #{phone} limit 0,1")
    int getUserID(@Param("phone") String phone);

    @Select("Select * from user natural join orders where order_id = #{orderId}")
    User getUserById(@Param("orderId") int orderId);

    @Delete("delete from user where userId =#{userId}")
    int deleteUser(@Param("userId") int userId);

    @Delete("delete from orders where userId =#{userId}")
    int deleteOrderByUser(@Param("userId") int userId);

    @Update("Update user SET username = #{username},trueName =#{trueName},identityNumber =#{identityNumber},mail =#{mail},sex =#{sex},type =#{type} WHERE phone=#{phone}")
    int editUser(@Param("phone") String phone,@Param("username") String username, @Param("trueName") String trueName, @Param("identityNumber") String identityNumber,  @Param("mail") String mail, @Param("sex") String sex, @Param("type") String type);

}
