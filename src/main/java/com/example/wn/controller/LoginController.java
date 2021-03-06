package com.example.wn.controller;

import com.example.wn.pojo.User;
import com.example.wn.result.Result;
import com.example.wn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        User user=userService.get(username,requestUser.getPassword());
        if (user == null) {
            String message = "账号密码错误";
            System.out.println(message);
            return new Result(400);
        } else {
            session.setAttribute("user",user);
            return new Result(200);
        }
    }
}


