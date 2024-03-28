package com.database.flightbook.service;

import com.alibaba.fastjson.JSONObject;
import com.database.flightbook.entity.Flight;
import com.database.flightbook.entity.OutputInformation;
import com.database.flightbook.mapper.FlightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service

public class FlightService {
    @Autowired
    FlightMapper flightMapper;

    public OutputInformation getAllFlights(String token) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String username = UserService.ifLogin(token);
        if (username.equals(UserService.manage_id)) {
            out.put("flightData", flightMapper.findallFlight());
        } else {
            return new OutputInformation(601);

        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation getOverFlights(String token) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String username = UserService.ifLogin(token);
        if (username.equals(UserService.manage_id)) {
            out.put("flightData", flightMapper.getOverFlights());
        } else {
            return new OutputInformation(601);

        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation getFlightMessage(String token, String flightName, String leaveTime) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            out.put("flightData", flightMapper.findFlightMessage(flightName, leaveTime));
        }
        outputInformation.setData(out);


        return outputInformation;
    }

    public OutputInformation addFlight(String token, String flightName, String
            leaveAirport, String arriveAirport, String leaveTime, String arriveTime, String capacity, String price, String leaveCity, String arriveCity,int timezone1,int timezone2) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            int code =flightMapper.addFlight( flightName, leaveAirport, arriveAirport,leaveTime,arriveTime,Integer.parseInt(capacity),Double.parseDouble(price),leaveCity,arriveCity,timezone1,timezone2);
            if (code == 1) {
                outputInformation = new OutputInformation(0);
            } else {
                outputInformation = new OutputInformation(-1);
            }
        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation editFlight(String token,int flightId, String flightName, String
            leaveAirport, String arriveAirport, String leaveTime, String arriveTime, String capacity, String price, String leaveCity, String arriveCity,int bookSum) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            int code =flightMapper.editFlight( flightId,flightName, leaveAirport, arriveAirport,leaveTime,arriveTime,Integer.parseInt(capacity),Double.parseDouble(price),leaveCity,arriveCity, bookSum);
            if (code == 1) {
                outputInformation = new OutputInformation(0);
            } else {
                outputInformation = new OutputInformation(-1);
            }
        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation deleteFlight(String token, int  flightId) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            int code = flightMapper.deleteFlight(flightId);
            flightMapper.removeFromOrderAndFlightByFlightId(flightId);
            if (code == 1) {
                outputInformation = new OutputInformation(0);
            } else {
                outputInformation = new OutputInformation(-1);

            }
        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation getDirectFlight(String token,String leaveTime,String leaveCity,String arriveCity) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            out.put("flightData", flightMapper.getDirectFlight(leaveTime,leaveCity,arriveCity));
        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation getLowDirectFlight(String token,String leaveCity,String arriveCity,int time) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            if(time==1){
                out.put("flightData", flightMapper.getOneDirectFlight(leaveCity,arriveCity));
            }else if(time==3){
                out.put("flightData", flightMapper.getThreeDirectFlight(leaveCity,arriveCity));
            }else if(time==6){
                out.put("flightData", flightMapper.getSixDirectFlight(leaveCity,arriveCity));
            }

        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation getLowDirectFlightbyLeaveCity(String token,String leaveCity) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {

                out.put("flightData", flightMapper.getLowDirectFlightbyLeaveCity(leaveCity));


        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation getMessageById(String token,int flightId) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {

            out.put("flightData", flightMapper.getMessageById(flightId));


        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation getLowTransferFlight(String token,String leaveCity,String arriveCity,int time) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            List<List<Flight>> result = new ArrayList<List<Flight>>();
            List<Flight> transferFirst=new ArrayList<>();
            if(time==1){
                transferFirst= flightMapper.getOneTransferFlightFirst(leaveCity,arriveCity);
            }else if(time==3){
                transferFirst= flightMapper.getThreeTransferFlightFirst(leaveCity,arriveCity);
            }else if(time==6){
                transferFirst=flightMapper.getSixTransferFlightFirst(leaveCity,arriveCity);
            }
            for(Flight flight:transferFirst){
                List<Flight> temp=flightMapper.getTransferFlightSecond(flight.getArriveTime(),flight.getArriveCity(),arriveCity);
                for(Flight t:temp){
                    List<Flight> aResult= new ArrayList<Flight>();
                    aResult.add(flight);
                    aResult.add(t);
                    result.add(aResult);

                }
            }
            out.put("flightData",result );

        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation getTransferFlight(String token,String leaveTime,String leaveCity,String arriveCity) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            List<List<Flight>> result = new ArrayList<List<Flight>>();

            List<Flight> transferFirst=flightMapper.getTransferFlightFirst(leaveTime,leaveCity,arriveCity);
            for(Flight flight:transferFirst){
                List<Flight> temp=flightMapper.getTransferFlightSecond(flight.getArriveTime(),flight.getArriveCity(),arriveCity);
                for(Flight t:temp){
                    List<Flight> aResult= new ArrayList<Flight>();
                    aResult.add(flight);
                    aResult.add(t);
                    result.add(aResult);

                }
            }
            out.put("flightData",result );
        }
        outputInformation.setData(out);


        return outputInformation;
    }

    public OutputInformation getChangeDirectFlight(String token,String leaveCity,String arriveCity) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            out.put("flightData", flightMapper.getChangeDirectFlight(leaveCity,arriveCity));
        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation getChangeTransferFlight(String token,String leaveCity,String arriveCity) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);

        JSONObject out = new JSONObject();
        String phone = UserService.ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            List<List<Flight>> result = new ArrayList<List<Flight>>();

            List<Flight> transferFirst=flightMapper.getChangeTransferFlightFirst(leaveCity,arriveCity);
            for(Flight flight:transferFirst){
                List<Flight> temp=flightMapper.getTransferFlightSecond(flight.getArriveTime(),flight.getArriveCity(),arriveCity);
                for(Flight t:temp){
                    List<Flight> aResult= new ArrayList<Flight>();
                    aResult.add(flight);
                    aResult.add(t);
                    result.add(aResult);

                }
            }
            out.put("flightData",result );
        }
        outputInformation.setData(out);


        return outputInformation;
    }

}
