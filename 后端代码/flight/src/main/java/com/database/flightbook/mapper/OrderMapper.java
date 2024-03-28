package com.database.flightbook.mapper;

import com.database.flightbook.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper

@Repository
public interface OrderMapper {

    @Insert("Insert  Into orders (`orderTime`,`userId`) VALUES (#{orderTime},#{userId})")
    int orderFlight(@Param("orderTime") String orderTime, @Param("userId") int userId);

    @Insert("Insert  Into orderandpassenger (`order_id`,`identityNumber`,`trueName`,`phone`) VALUES (#{order_id},#{identityNumber},#{trueName},#{phone})")
    int passengerAndOrderInsert(@Param("order_id") int order_id, @Param("phone") String phone, @Param("identityNumber") String identityNumber, @Param("trueName") String trueName);

    @Insert("Insert  Into orderandflight (`order_id`,`flight_id`) VALUES (#{order_id},#{flight_id})")
    int orderandflightInsert(@Param("order_id") int order_id, @Param("flight_id") int flight_id);

    @Select("Select * from orders  where userId =#{userId} and orderTime=#{orderTime}")
    int getOrderId(@Param("orderTime") String orderTime, @Param("userId") int userId);

    @Select("Select * from orders natural join user natural join flight  natural join orderandflight where userId =#{userId} and date_sub(leave_time, interval timezone1-8 hour) <= now() and state=0")
    List<OrderMessage> getOverOrders(@Param("userId") int userId);

    @Select("Select * from orders natural join user natural join flight  natural join orderandflight where userId =#{userId} and date_sub(leave_time, interval timezone1-8 hour) > now() and state=0")
    List<OrderMessage> getNoOverOrders(@Param("userId") int userId);

    @Select("Select * from orderandpassenger  where order_id =#{orderId} ")
    List<Passenger> getPassengerByOrderId(@Param("orderId") int orderId);

    @Select("Select * from orderandflight natural join flight where order_id =#{orderId} ")
    List<Flight> getFlightByOrderId(@Param("orderId") int orderId);

    @Select("Select * from orders natural join user natural join flight  natural join orderandflight where userId =#{userId} and state=-1")
    List<OrderMessage> getRefundOrders(@Param("userId") int userId);

    @Update("Update orders SET state=-1 WHERE order_id=#{orderId}")
    int refund(@Param("orderId") int orderId);


    @Select("Select * from orders natural join user natural join flight  natural join orderandflight where date_sub(leave_time, interval timezone1-8 hour) > now() and state=0")
    List<OrderMessage> getAllNoOverOrders();

    @Select("Select * from orders natural join user natural join flight  natural join orderandflight where date_sub(leave_time, interval timezone1-8 hour) <= now() and state=0")
    List<OrderMessage> getAllOverOrders();

    @Select("Select * from orders natural join user natural join flight  natural join orderandflight where state=-1")
    List<OrderMessage> getAllRefundOrders();

    @Update("Update flight SET book_sum=book_sum+#{count} WHERE flight_id=#{flightId}")
    int downCount(@Param("flightId") int flightId, @Param("count") int count);

    @Update("Update flight SET book_sum=book_sum-#{count} WHERE flight_id=#{flightId}")
    int upCount(@Param("flightId") int flightId, @Param("count") int count);

    @Delete("delete from orderandflight where flight_id =#{flightId} and order_id=#{orderId}")
    int removeFromOrderAndFlight(@Param("flightId") int flightId, @Param("orderId") int orderId);

    @Delete("delete from orders where order_id =#{orderId}")
    int deleteOrder(@Param("orderId") int orderId);

    @Delete("delete from orderandflight where  order_id=#{orderId}")
    int removeFromOrderAndFlightByOrderId( @Param("orderId") int orderId);



    @Delete("delete from orderandpassenger where order_id=#{orderId}")
    int removeFromOrderAndPassenger(@Param("orderId") int orderId);
}
