package com.database.flightbook.controller;

import com.database.flightbook.entity.OutputInformation;
import com.database.flightbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public OutputInformation login(String phone, String psw) throws IOException {
        return userService.login(phone, psw);
    }

    @PostMapping(value = "/registe")
    public OutputInformation registe(String username, String trueName, String identityNumber, String phone, String mail, String sex, String type, String password
    ) throws IOException {
        return userService.registe(username, trueName, identityNumber, phone, mail, sex, type, password);
    }

    @PostMapping(value = "/forget")
    public OutputInformation forget(String identityNumber, String phone, String password) throws IOException {
        return userService.forget(identityNumber, phone, password);
    }

    @PostMapping("/getUser")
    public OutputInformation getUser(@RequestHeader(value = "token") String token) throws IOException {
        return userService.getUser(token);
    }

    @PostMapping("/manageLogin")
    public OutputInformation manageLogin(String username, String password) throws IOException {
        return userService.manageLogin(username,password);
    }

    @PostMapping("/getAllUsers")
    public OutputInformation getAllUsers(@RequestHeader(value = "token") String token) throws IOException {
        return userService.getAllUsers(token);
    }
    @PostMapping("/getUserById")
    public OutputInformation getUserById(@RequestHeader(value = "token") String token,int orderId) throws IOException {
        return userService.getUserById(token,orderId);
    }
    @PostMapping("/deleteUser")
    public OutputInformation deleteUser(@RequestHeader(value = "token") String token,int userId) throws IOException {
        return userService.deleteUser(token,userId);
    }
    @PostMapping(value = "/editUser")
    public OutputInformation editUser(@RequestHeader(value = "token") String token,String username, String trueName, String identityNumber,  String mail, String sex, String type
    ) throws IOException {
        return userService.editUser(token,username, trueName, identityNumber, mail, sex, type);
    }
}
