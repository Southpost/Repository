package com.sacc.Controller;

import com.sacc.Repository.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterController {
    @Autowired
     Register reg;


    @PostMapping("/api/register")
    public String Register(String studentNumber, String name, String email, HttpServletRequest request){

        if(!StringUtils.hasText(studentNumber)||!StringUtils.hasText(name)||!StringUtils.hasText(email)) {
            return "请将信息填写完整";
        }
//        if(reg.hasRegister(name)) {
//            return "该用户已完成注册";
//        }
        if(reg.isEmail(email)) {
            return "不合法邮箱";
        }

        reg.register(studentNumber,name,email);
        HttpSession httpSession=request.getSession();
        httpSession.setMaxInactiveInterval(60*60);
        return "注册成功";
    }

}
