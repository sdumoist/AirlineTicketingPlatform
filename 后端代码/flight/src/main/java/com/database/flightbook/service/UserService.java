package com.database.flightbook.service;

import com.alibaba.fastjson.JSONObject;
import com.database.flightbook.entity.Mytoken;
import com.database.flightbook.entity.OutputInformation;
import com.database.flightbook.entity.User;
import com.database.flightbook.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    public static String manage_id="admin";
    public static String manage_psw="admin";
    @Value("${expireTime}")
    private long expireTime;
    @Autowired
    UserMapper userMapper;
    public static String ifLogin(String token) {
        Mytoken myToken = Mytoken.valid(token);
        if (myToken != null) {

            return myToken.getUserId();
        } else {
            return null;
        }

    }
    public OutputInformation login(String phone, String psw) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);
        JSONObject out = new JSONObject();

        User s = userMapper.login(phone, psw);
        if (s != null) {
            out.put("userData", s);
            Mytoken myToken = new Mytoken(phone.toString(), System.currentTimeMillis() + expireTime);
            out.put("token", myToken.generate());
        } else {
            outputInformation = new OutputInformation(1, "账号或密码错误");
        }

        outputInformation.setData(out);


        return outputInformation;
    }

    public OutputInformation registe(String username, String trueName, String identityNumber, String phone, String mail, String sex, String type, String password) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        User isRe = userMapper.isRegiste(phone);
        if (isRe != null) {
            outputInformation = new OutputInformation(1, "已注册");
        } else {
            int code = userMapper.registe(username, trueName, identityNumber, phone, mail, sex, type, password);
            if (code == 1) {
                outputInformation = new OutputInformation(0);
            } else {
                outputInformation = new OutputInformation(-1);
            }
        }

        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation editUser(String token,String username, String trueName, String identityNumber, String mail, String sex, String type) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String phone = ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else  {
            int code = userMapper.editUser(phone,username, trueName, identityNumber, mail, sex, type);
            if (code == 1) {
                outputInformation = new OutputInformation(0);
            } else {
                outputInformation = new OutputInformation(-1);
            }
        }

        outputInformation.setData(out);


        return outputInformation;
    }

    public OutputInformation forget(String identityNumber,String phone, String password) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);
        JSONObject out = new JSONObject();
        if(userMapper.readyForget(identityNumber).equals(phone)){
            int code = userMapper.forget(identityNumber, password);

            if (code == 1) {
                outputInformation = new OutputInformation(0);
            } else {
                outputInformation = new OutputInformation(-1);
            }
        }else {
            outputInformation = new OutputInformation(1,"身份证号码与电话号码不一致");
        }


        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation getUser(String token) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String phone = ifLogin(token);
        if (phone == null) {
            return new OutputInformation(601);
        } else {
            out.put("userData", userMapper.isRegiste(phone));
        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation getAllUsers(String token) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String username = ifLogin(token);
        if (username.equals(manage_id)) {
            out.put("userData", userMapper.getAllUsers());
        } else {
            return new OutputInformation(601);

        }
        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation manageLogin(String username, String password) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);
        JSONObject out = new JSONObject();


        if (username.equals(manage_id)&&password.equals(manage_psw)) {

            Mytoken myToken = new Mytoken(manage_id, System.currentTimeMillis() + expireTime);
            out.put("token", myToken.generate());
        } else {
            outputInformation = new OutputInformation(1, "账号或密码错误");
        }

        outputInformation.setData(out);


        return outputInformation;
    }
    public OutputInformation getUserById(String token, int orderId) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String username = UserService.ifLogin(token);
        if (username.equals(UserService.manage_id)) {
            out.put("userData", userMapper.getUserById(orderId));

        } else {
            return new OutputInformation(601);
        }
        outputInformation.setData(out);
        return outputInformation;
    }
    public OutputInformation deleteUser(String token, int userId) throws IOException {
        OutputInformation outputInformation = new OutputInformation(0);


        JSONObject out = new JSONObject();
        String username = UserService.ifLogin(token);
        if (username.equals(UserService.manage_id)) {
            userMapper.deleteUser(userId);
            userMapper.deleteOrderByUser(userId);

        } else {
            return new OutputInformation(601);
        }
        outputInformation.setData(out);
        return outputInformation;
    }
}
