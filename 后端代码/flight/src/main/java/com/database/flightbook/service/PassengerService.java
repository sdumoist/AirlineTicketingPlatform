package com.database.flightbook.service;

import com.alibaba.fastjson.JSONObject;
import com.database.flightbook.entity.Flight;
import com.database.flightbook.entity.Mytoken;
import com.database.flightbook.entity.OutputInformation;
import com.database.flightbook.entity.User;
import com.database.flightbook.mapper.FlightMapper;
import com.database.flightbook.mapper.OrderMapper;
import com.database.flightbook.mapper.PassengerMapper;
import com.database.flightbook.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {
    @Autowired
    PassengerMapper passengerMapper;

    @Autowired
    UserMapper userMapper;
    public OutputInformation addPassenger( String token, String phone, String identityNumber , String trueName) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String myphone = UserService.ifLogin(token);
        if (myphone == null) {
            return new OutputInformation(601);
        } else {

            int userId=userMapper.getUserID(myphone);

            int code = passengerMapper.addPassenger(userId,phone,identityNumber,trueName);
            if (code == 1) {
                outputInformation = new OutputInformation(0);
            } else {
                outputInformation = new OutputInformation(-1);
            }
        }
        outputInformation.setData(out);
        return outputInformation;
    }
    public OutputInformation getMyPassengers(String token) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            int userId=userMapper.getUserID(phone);
            out.put("passengerData", passengerMapper.getMyPassengers(userId));


        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation deletePassenger(String token,String phone,String identityNumber ,String trueName) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String myphone = UserService.ifLogin(token);
        if (myphone == null) {
            return new OutputInformation(601);
        } else {
            int userId=userMapper.getUserID(myphone);
            int code = passengerMapper.deletePassenger(userId,phone,identityNumber,trueName);
            if (code == 1) {
                outputInformation = new OutputInformation(0);
            } else {
                outputInformation = new OutputInformation(-1);

            }


        }
        outputInformation.setData(out);


        return outputInformation;
    }
}
