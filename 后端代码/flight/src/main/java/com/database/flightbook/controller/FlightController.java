package com.database.flightbook.controller;

import com.database.flightbook.entity.OutputInformation;
import com.database.flightbook.service.FlightService;
import com.database.flightbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin
@RestController
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping("/getAllFlights")
    public OutputInformation getAllFlights(@RequestHeader(value = "token") String token) throws IOException {
        return flightService.getAllFlights(token);
    }
    @PostMapping("/getOverFlights")
    public OutputInformation getOverFlights(@RequestHeader(value = "token") String token) throws IOException {
        return flightService.getOverFlights(token);
    }

    @PostMapping("/getFlightMessage")
    public OutputInformation getFlightMessage(@RequestHeader(value = "token") String token, String flightName, String leaveTime) throws IOException {
        return flightService.getFlightMessage(token, flightName, leaveTime);
    }

    @PostMapping("/addFlight")
    public OutputInformation addFlight(@RequestHeader(value = "token") String token, String flightName, String
            leaveAirport, String arriveAirport, String leaveTime, String arriveTime, String capacity, String price,String leaveCity,String arriveCity,int timezone1,int timezone2) throws IOException {
        return flightService.addFlight(token, flightName, leaveAirport, arriveAirport,leaveTime,arriveTime,capacity,price,leaveCity,arriveCity,timezone1,timezone2);
    }
    @PostMapping("/deleteFlight")
    public OutputInformation deleteFlight(@RequestHeader(value = "token") String token, int flightId) throws IOException {
        return flightService.deleteFlight(token, flightId);
    }
    @PostMapping("/getDirectFlight")
    public OutputInformation getDirectFlight(@RequestHeader(value = "token") String token,String leaveTime,String leaveCity,String arriveCity) throws IOException {
        return flightService.getDirectFlight(token, leaveTime,leaveCity,arriveCity);
    }
    @PostMapping("/getTransferFlight")
    public OutputInformation getTransferFlight(@RequestHeader(value = "token") String token,String leaveTime,String leaveCity,String arriveCity) throws IOException {
        return flightService.getTransferFlight(token, leaveTime,leaveCity,arriveCity);
    }

    @PostMapping("/editFlight")
    public OutputInformation editFlight(@RequestHeader(value = "token") String token,int flightId,String flightName, String
            leaveAirport, String arriveAirport, String leaveTime, String arriveTime, String capacity, String price,String leaveCity,String arriveCity,int bookSum) throws IOException {
        return flightService.editFlight(token, flightId,flightName, leaveAirport, arriveAirport,leaveTime,arriveTime,capacity,price,leaveCity,arriveCity,bookSum);
    }
    @PostMapping("/getLowDirectFlight")
    public OutputInformation getLowDirectFlight(@RequestHeader(value = "token") String token,String leaveCity,String arriveCity,int time) throws IOException {
        return flightService.getLowDirectFlight(token, leaveCity,arriveCity,time);
    }
    @PostMapping("/getLowTransferFlight")
    public OutputInformation getLowTransferFlight(@RequestHeader(value = "token") String token,String leaveCity,String arriveCity,int time) throws IOException {
        return flightService.getLowTransferFlight(token, leaveCity,arriveCity,time);
    }
    @PostMapping("/getLowTransferFlightbyLeaveCity")
    public OutputInformation getLowDirectFlightbyLeaveCity(@RequestHeader(value = "token") String token,String leaveCity) throws IOException {
        return flightService.getLowDirectFlightbyLeaveCity(token, leaveCity);
    }
    @PostMapping("/getMessageById")
    public OutputInformation getMessageById(@RequestHeader(value = "token") String token,int flightId) throws IOException {
        return flightService.getMessageById(token, flightId);
    }


    @PostMapping("/getChangeDirectFlight")
    public OutputInformation getChangeDirectFlight(@RequestHeader(value = "token") String token,String leaveCity,String arriveCity) throws IOException {
        return flightService.getChangeDirectFlight(token, leaveCity,arriveCity);
    }
    @PostMapping("/getChangeTransferFlight")
    public OutputInformation getChangeTransferFlight(@RequestHeader(value = "token") String token,String leaveCity,String arriveCity) throws IOException {
        return flightService.getChangeTransferFlight(token, leaveCity,arriveCity);
    }
}
