package com.database.flightbook.mapper;

import com.database.flightbook.entity.Flight;
import com.database.flightbook.entity.Orders;
import com.database.flightbook.entity.Passenger;
import com.database.flightbook.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper

@Repository
public interface PassengerMapper {
    @Insert("Insert  Into passenger (`userId`,`phone`,`identityNumber`,`trueName`) VALUES (#{userId},#{phone},#{identityNumber},#{trueName})")
    int addPassenger(@Param("userId") int userId, @Param("phone") String phone,@Param("identityNumber") String identityNumber ,@Param("trueName") String trueName);

    @Select("Select * from passenger where  userId=#{userId} ")
    List<Passenger> getMyPassengers(@Param("userId") int userId);

    @Delete("delete from passenger where userId=#{userId} and phone=#{phone} and identityNumber=#{identityNumber} and trueName=#{trueName}")
    int deletePassenger(@Param("userId") int userId, @Param("phone") String phone,@Param("identityNumber") String identityNumber ,@Param("trueName") String trueName);

}
