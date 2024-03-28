package com.database.flightbook.mapper;

import com.database.flightbook.entity.Flight;
import com.database.flightbook.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper

@Repository
public interface FlightMapper {

    @Select("Select * from flight where date_sub(leave_time, interval timezone1-8 hour)> now()")
    List<Flight> findallFlight();

    @Select("Select * from flight where date_sub(leave_time, interval timezone1-8 hour) < now()")
    List<Flight> getOverFlights();

    @Select("Select * from flight where locate(#{flightName},flight_name) >0 and DATE_FORMAT( `leave_time`, \"%Y-%m-%d\" )=#{leaveTime}  ")
    List<Flight> findFlightMessage(@Param("flightName") String flightName, @Param("leaveTime") String leaveTime);

    @Insert("Insert  Into flight (`flight_name`,`leave_city`,`arrive_city`,`leave_airport`,`arrive_airport`,`leave_time`,`arrive_time`,`capacity`,`price`,`timezone1`,`timezone2`) VALUES (#{flightName},#{leaveCity},#{arriveCity},#{leaveAirport},#{arriveAirport},#{leaveTime},#{arriveTime},#{capacity},#{price},#{timezone1},#{timezone2})")
    int addFlight(@Param("flightName") String flightName, @Param("leaveAirport") String
            leaveAirport, @Param("arriveAirport") String arriveAirport, @Param("leaveTime") String leaveTime, @Param("arriveTime") String arriveTime, @Param("capacity") int capacity, @Param("price") double price, @Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity,@Param("timezone1") int timezone1,@Param("timezone2") int timezone2);


    @Delete("delete from flight where flight_id =#{flightId}")
    int deleteFlight(@Param("flightId") int flightId);


    @Select("Select * from flight where DATE_FORMAT( `leave_time`, \"%Y-%m-%d\" )=#{leaveTime} and arrive_city=#{arriveCity} and leave_city=#{leaveCity} and capacity>book_sum")
    List<Flight> getDirectFlight(@Param("leaveTime") String leaveTime, @Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity);

    @Select("Select * from flight where DATE_FORMAT( `leave_time`, \"%Y-%m-%d\" )=#{leaveTime} and arrive_city !=#{arriveCity} and leave_city=#{leaveCity} and capacity>book_sum")
    List<Flight> getTransferFlightFirst(@Param("leaveTime") String leaveTime, @Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity);

    @Select("Select * from flight where leave_time> date_add(#{leaveTime}, interval 1 hour) and leave_time < date_add(#{leaveTime}, interval 1 day) and arrive_city =#{arriveCity} and leave_city=#{leaveCity} and capacity>book_sum")
    List<Flight> getTransferFlightSecond(@Param("leaveTime") Timestamp leaveTime, @Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity);

    @Update("Update flight SET flight_name=#{flightName},leave_airport =#{leaveAirport},arrive_airport =#{arriveAirport},leave_time= #{leaveTime},arrive_time=#{arriveTime},capacity=#{capacity},price=#{price},leave_city=#{leaveCity},arrive_city=#{arriveCity},book_sum=#{bookSum} WHERE flight_id=#{flightId}")
    int editFlight(@Param("flightId") int flightId, @Param("flightName") String flightName, @Param("leaveAirport") String
            leaveAirport, @Param("arriveAirport") String arriveAirport, @Param("leaveTime") String leaveTime, @Param("arriveTime") String arriveTime, @Param("capacity") int capacity, @Param("price") double price, @Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity, int bookSum);


    @Select("Select * from flight where date_sub(leave_time, interval timezone1-8 hour)<date_add(NOW(), interval 1 MONTH) and date_sub(leave_time, interval timezone1-8 hour)> now() and arrive_city=#{arriveCity} and leave_city=#{leaveCity} and capacity>book_sum")
    List<Flight> getOneDirectFlight(@Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity);

    @Select("Select * from flight where date_sub(leave_time, interval timezone1-8 hour)<date_add(NOW(), interval 3 MONTH) and date_sub(leave_time, interval timezone1-8 hour)> now() and arrive_city=#{arriveCity} and leave_city=#{leaveCity} and capacity>book_sum")
    List<Flight> getThreeDirectFlight(@Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity);

    @Select("Select * from flight where date_sub(leave_time, interval timezone1-8 hour)<date_add(NOW(), interval 6 MONTH) and date_sub(leave_time, interval timezone1-8 hour)> now() and arrive_city=#{arriveCity} and leave_city=#{leaveCity} and capacity>book_sum")
    List<Flight> getSixDirectFlight(@Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity);

    @Select("Select * from flight where date_sub(leave_time, interval timezone1-8 hour)<date_add(NOW(), interval 1 MONTH) and date_sub(leave_time, interval timezone1-8 hour)> now() and arrive_city !=#{arriveCity} and leave_city=#{leaveCity} and capacity>book_sum")
    List<Flight> getOneTransferFlightFirst(@Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity);

    @Select("Select * from flight where date_sub(leave_time, interval timezone1-8 hour)<date_add(NOW(), interval 3 MONTH) and date_sub(leave_time, interval timezone1-8 hour)> now() and arrive_city !=#{arriveCity} and leave_city=#{leaveCity} and capacity>book_sum")
    List<Flight> getThreeTransferFlightFirst(@Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity);

    @Select("Select * from flight where date_sub(leave_time, interval timezone1-8 hour)<date_add(NOW(), interval 6 MONTH) and date_sub(leave_time, interval timezone1-8 hour)> now() and arrive_city !=#{arriveCity} and leave_city=#{leaveCity} and capacity>book_sum")
    List<Flight> getSixTransferFlightFirst(@Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity);

    @Select("Select * from flight where date_sub(leave_time, interval timezone1-8 hour)<date_add(NOW(), interval 6 MONTH) and date_sub(leave_time, interval timezone1-8 hour)> now() and  leave_city=#{leaveCity} and capacity>book_sum order by price limit 0,10")
    List<Flight> getLowDirectFlightbyLeaveCity(@Param("leaveCity") String leaveCity);

    @Select("Select * from flight where flight_id=#{flightId}")
    Flight getMessageById(@Param("flightId") int flightId);


    @Select("Select * from flight where  arrive_city=#{arriveCity}and date_sub(leave_time, interval timezone1-8 hour)> now() and leave_city=#{leaveCity} and capacity>book_sum")
    List<Flight> getChangeDirectFlight(@Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity);

    @Select("Select * from flight where   arrive_city !=#{arriveCity} and date_sub(leave_time, interval timezone1-8 hour)> now()and leave_city=#{leaveCity} and capacity>book_sum")
    List<Flight> getChangeTransferFlightFirst(@Param("leaveCity") String leaveCity, @Param("arriveCity") String arriveCity);

    @Delete("delete from orderandflight where flight_id =#{flightId}")
    int removeFromOrderAndFlightByFlightId(@Param("flightId") int flightId);
}
