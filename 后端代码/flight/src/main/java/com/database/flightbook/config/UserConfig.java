package com.database.flightbook.config;


import com.database.flightbook.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {


    @Bean //产生一个Bean对象，然后这个Bean对象交给Spring管理 这些bean都需要在@Configuration注解下进行创建
    public User user() {
        User user = new User();

        return user;
    }
}


