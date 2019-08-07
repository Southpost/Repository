package com.sacc.Controller;

import com.mongodb.diagnostics.logging.Logger;
import com.sacc.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

public class RegisterController {
    @Autowired
    RegisterRepository reg;


    @PostMapping("/api/register")
    public String register(String studentNumber, String name, String email, HttpServletRequest request){

        if(!StringUtils.hasText(studentNumber)||!StringUtils.hasText(name)||!StringUtils.hasText(email)) {
            return "请将信息填写完整";
        }
//       if(reg.hasRegister(name)) {
//            return "该用户已完成注册";
//       }
        if(reg.isEmail(email)) {
            return "不合法邮箱";
        }

        reg.register(studentNumber,name,email);
        HttpSession httpSession=request.getSession();
        httpSession.setMaxInactiveInterval(60*60);
        return "注册成功";
    }


    /**
    * 发送邮箱验证码
    * @param email   接收者的邮箱 【注册人】
    * @param request
    * @return
    */
    @PostMapping("/verify_code")
    public String verifyCode(@RequestParam(value = "email",defaultValue = "",required = true) String email,
                             HttpServletRequest request){
        try{
            String code=RandomTools.Randomcode();
            request.getSession().setAttribute("registerCode",code);
            Logger log = null;
            assert false;
            log.info("开始发送邮件验证码----");
            reg.sendRegisterCode(email,code);
        }catch (Exception e){
            e.printStackTrace();
            return "获取失败";
        }

        return "成功！";
    }


    public static class RandomTools{
        static String Randomcode(){
            char [] codeSequence = {'A','B','C','D','E','F','G','0','1','2','3','4','5','6','7','8','9'};
            //动态字符串
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            int count =0;
            while(true){
                int index = random.nextInt(codeSequence.length); //定义随机数的范围并且产生一个随机的下标
                //随机的取出一个数
                char c = codeSequence[index];
                if (sb.indexOf(c+"")==-1) { //不让字符重复,并且把一个字符变成字符串
                    sb.append(c);
                    count++;
                    if (count==4) {
                        break;
                    }
                }
            }
            return sb.toString();

        }
    }

}
