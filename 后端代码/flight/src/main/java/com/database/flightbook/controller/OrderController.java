package com.database.flightbook.controller;

import com.alipay.api.AlipayApiException;
import com.database.flightbook.entity.OutputInformation;
import com.database.flightbook.service.FlightService;
import com.database.flightbook.service.OrderService;
import com.database.flightbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/orderFlight")
    public OutputInformation orderDirectFlight(@RequestHeader(value = "token") String token,@RequestParam(value = "flightId[]")int flightId[],@RequestParam(value = "passenger[]") String passenger[],@RequestParam(value = "allPrice[]")double allPrice[]) throws IOException, AlipayApiException {
        return orderService.orderDirectFlight(token,flightId,passenger,allPrice);


    }



    @PostMapping("/getOverOrders")
    public OutputInformation getOverOrders(@RequestHeader(value = "token") String token) throws IOException {
        return orderService.getOverOrders(token);
    }
    @PostMapping("/getNoOverOrders")
    public OutputInformation getNoOverOrders(@RequestHeader(value = "token") String token) throws IOException {
        return orderService.getNoOverOrders(token);
    }

    @PostMapping("/getRefundOrders")
    public OutputInformation getRefundOrders(@RequestHeader(value = "token") String token) throws IOException {
        return orderService.getRefundOrders(token);
    }
    @PostMapping("/getAllNoOverOrders")
    public OutputInformation getAllNoOverOrders(@RequestHeader(value = "token") String token) throws IOException {
        return orderService.getAllNoOverOrders(token);
    }
    @PostMapping("/getAllOverOrders")
    public OutputInformation getAllOverOrders(@RequestHeader(value = "token") String token) throws IOException {
        return orderService.getAllOverOrders(token);
    }

    @PostMapping("/getAllRefundOrders")
    public OutputInformation getAllRefundOrders(@RequestHeader(value = "token") String token) throws IOException {
        return orderService.getAllRefundOrders(token);
    }
    @PostMapping("/getDataByOrderId")
    public OutputInformation getPassengerByOrderId(@RequestHeader(value = "token") String token,int orderId) throws IOException {
        return orderService.getDataByOrderId(token,orderId);
    }

    @PostMapping("/refund")
    public OutputInformation refund(@RequestHeader(value = "token") String token,int orderId) throws IOException {
        return orderService.refund(token,orderId);
    }

    @PostMapping("/orderChange")
    public OutputInformation orderChange(@RequestHeader(value = "token") String token,@RequestParam(value = "flightId[]")int flightId[],@RequestParam(value = "orderId") int orderId) throws IOException {
        return orderService.orderChange(token,flightId,orderId);
    }
    @PostMapping("/deleteNoOverOrder")
    public OutputInformation deleteNoOverOrder(@RequestHeader(value = "token") String token,int orderId) throws IOException {
        return orderService.deleteNoOverOrder(token,orderId);
    }

    @PostMapping("/deleteOverOrder")
    public OutputInformation deleteOverOrder(@RequestHeader(value = "token") String token,int orderId) throws IOException {
        return orderService.deleteOverOrder(token,orderId);
    }

}
