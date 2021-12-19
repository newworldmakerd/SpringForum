package com.example.forum_test.controller;

import com.example.forum_test.mapper.UserMapper;
import com.example.forum_test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class GreetingController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/")
    public String index(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user= userMapper.findByToken(token);
                if(user!=null){
                    req.getSession().setAttribute("user",user);
                }
                break;
            }
        }


        return "index";
    }

}