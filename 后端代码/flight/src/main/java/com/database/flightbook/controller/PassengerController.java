package com.database.flightbook.controller;

import com.database.flightbook.entity.OutputInformation;
import com.database.flightbook.service.FlightService;
import com.database.flightbook.service.OrderService;
import com.database.flightbook.service.PassengerService;
import com.database.flightbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin
@RestController
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @PostMapping("/addPassenger")
    public OutputInformation addPassenger(@RequestHeader(value = "token") String token,String phone,String identityNumber ,String trueName) throws IOException {
        return passengerService.addPassenger(token,phone,identityNumber,trueName);
    }

    @PostMapping("/getMyPassengers")
    public OutputInformation getMyPassengers(@RequestHeader(value = "token") String token) throws IOException {
        return passengerService.getMyPassengers(token);
    }
    @PostMapping("/deletePassenger")
    public OutputInformation deletePassenger(@RequestHeader(value = "token") String token,String phone,String identityNumber ,String trueName) throws IOException {
        return passengerService.deletePassenger(token,phone,identityNumber,trueName);
    }
}
