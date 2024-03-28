package com.database.flightbook.service;

import com.alibaba.fastjson.JSONObject;
import com.database.flightbook.entity.*;
import com.database.flightbook.mapper.FlightMapper;
import com.database.flightbook.mapper.OrderMapper;
import com.database.flightbook.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.text.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    public OutputInformation orderDirectFlight(String token, int[] flightId, String[] passenger,double[] allPrice) throws IOException {


        OutputInformation outputInformation = new OutputInformation(0);
        System.out.println(allPrice[0]);
        JSONObject out = new JSONObject();
        String phones = UserService.ifLogin(token);
        if (phones == null) {
            return new OutputInformation(601);
        } else {
            int userId = userMapper.getUserID(phones);

            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String now = ft.format(dNow);
            int code = orderMapper.orderFlight(now, userId);
            if (code == 1) {
                int orderId = orderMapper.getOrderId(now, userId);
                System.out.println(passenger);
                for (String p : passenger) {
                    System.out.println(p);
                    JSONObject object = JSONObject
                            .parseObject(p);
                    String identityNumber = object.getString("identityNumber");
                    if (identityNumber.equals("")) {
                        continue;
                    }
                    String trueName = object.getString("trueName");
                    String phone = object.getString("phone");
                    orderMapper.passengerAndOrderInsert(orderId, phone, identityNumber, trueName);

                }


                for (int flight : flightId) {
                    orderMapper.downCount(flight, passenger.length - 1);
                    orderMapper.orderandflightInsert(orderId, flight);
                }
                outputInformation = new OutputInformation(0);
            } else {
                outputInformation = new OutputInformation(-1);
            }


        }


        return outputInformation;
    }

    public OutputInformation getOverOrders(String token) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            int userId = userMapper.getUserID(phone);
            out.put("orderData", orderMapper.getOverOrders(userId));
        }
        outputInformation.setData(out);
        return outputInformation;
    }

    public OutputInformation getAllNoOverOrders(String token) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String username = UserService.ifLogin(token);
        if (username.equals(UserService.manage_id)) {
            out.put("orderData", orderMapper.getAllNoOverOrders());
        } else {
            return new OutputInformation(601);
        }
        outputInformation.setData(out);
        return outputInformation;
    }

    public OutputInformation getAllOverOrders(String token) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String username = UserService.ifLogin(token);
        if (username.equals(UserService.manage_id)) {
            out.put("orderData", orderMapper.getAllOverOrders());
        } else {
            return new OutputInformation(601);
        }
        outputInformation.setData(out);
        return outputInformation;
    }

    public OutputInformation getAllRefundOrders(String token) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String username = UserService.ifLogin(token);
        if (username.equals(UserService.manage_id)) {
            out.put("orderData", orderMapper.getAllRefundOrders());
        } else {
            return new OutputInformation(601);
        }
        outputInformation.setData(out);
        return outputInformation;
    }

    public OutputInformation getNoOverOrders(String token) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            int userId = userMapper.getUserID(phone);
            out.put("orderData", orderMapper.getNoOverOrders(userId));
        }
        outputInformation.setData(out);
        return outputInformation;
    }

    public OutputInformation getDataByOrderId(String token, int orderId) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {

            out.put("passengerData", orderMapper.getPassengerByOrderId(orderId));
            out.put("flightData", orderMapper.getFlightByOrderId(orderId));
        }
        outputInformation.setData(out);
        return outputInformation;
    }

    public OutputInformation getRefundOrders(String token) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            int userId = userMapper.getUserID(phone);
            out.put("orderData", orderMapper.getRefundOrders(userId));
        }
        outputInformation.setData(out);
        return outputInformation;
    }

    public OutputInformation refund(String token, int orderId) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            //原来的票补回来
            List<Flight> oldFlight = orderMapper.getFlightByOrderId(orderId);
            List<Passenger> oldP = orderMapper.getPassengerByOrderId(orderId);

            for (Flight f : oldFlight) {
                orderMapper.upCount((int) f.getFlightId(), oldP.size());

            }
            int code = orderMapper.refund(orderId);

            if (code == 1) {
                outputInformation = new OutputInformation(0);
            } else {
                outputInformation = new OutputInformation(-1);
            }

        }
        outputInformation.setData(out);
        return outputInformation;
    }

    public OutputInformation orderChange(String token, int[] flightId, int orderId) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);
        JSONObject out = new JSONObject();
        String phones = UserService.ifLogin(token);
        if (phones == null) {
            return new OutputInformation(601);
        } else {
            //原来的票补回来
            List<Flight> oldFlight = orderMapper.getFlightByOrderId(orderId);
            List<Passenger> oldP = orderMapper.getPassengerByOrderId(orderId);

            for (Flight f : oldFlight) {
                orderMapper.upCount((int) f.getFlightId(), oldP.size());
                orderMapper.removeFromOrderAndFlight((int) f.getFlightId(), orderId);
            }
            //更新orderandflight表
            for (int flight : flightId) {
                orderMapper.downCount(flight, oldP.size());
                orderMapper.orderandflightInsert(orderId, flight);
            }


        }


        return outputInformation;
    }

    public OutputInformation deleteNoOverOrder(String token, int orderId) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String username = UserService.ifLogin(token);
        if (username.equals(UserService.manage_id)) {

            List<Flight> oldFlight = orderMapper.getFlightByOrderId(orderId);
            List<Passenger> oldP = orderMapper.getPassengerByOrderId(orderId);
            for (Flight f : oldFlight) {
                orderMapper.upCount((int) f.getFlightId(), oldP.size());
            }
            int code1 = orderMapper.removeFromOrderAndPassenger(orderId);
            int code2 = orderMapper.removeFromOrderAndFlightByOrderId(orderId);
            int code3 = orderMapper.deleteOrder(orderId);

                outputInformation = new OutputInformation(0);

        } else {
            return new OutputInformation(601);
        }
        outputInformation.setData(out);
        return outputInformation;
    }

    public OutputInformation deleteOverOrder(String token, int orderId) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String username = UserService.ifLogin(token);
        if (username.equals(UserService.manage_id)) {
            int code1 = orderMapper.removeFromOrderAndPassenger(orderId);
            int code2 = orderMapper.removeFromOrderAndFlightByOrderId(orderId);
            int code3 = orderMapper.deleteOrder(orderId);

        } else {
            return new OutputInformation(601);
        }
        outputInformation.setData(out);
        return outputInformation;
    }
}
