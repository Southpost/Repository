package com.sacc.Repository;

import com.sacc.Model.RegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 追梦
 */
@Component
public class RegisterRepository {
    @Autowired
    private MongoTemplate mongo;
    @Autowired
    private JavaMailSenderImpl javaMailSender;


    private Object NormalTools;


    public List<RegisterRepository> hasRegister(String username) {
        Query query = new Query(Criteria.where("userName").is(username));
        return mongo.find(query, RegisterRepository.class);
    }

    public void register(String studentNumber, String userName, String email) {
        Query query = new Query(Criteria.where("userName").is(userName));
        RegisterRepository result = (RegisterRepository) mongo.find(query, RegisterRepository.class);
        if (result != null) {
            return;
        }

        RegisterModel register = new RegisterModel();
        try {
            register.setStudentNumber(studentNumber);
            register.setEmail(email);
            register.setName(userName);
            mongo.insert(register);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendNormalEmail(String title, boolean titleWithName,
                                String content, boolean contentWithName, String email) {
        String dName = "shiLei";
        MimeMessage mimeMessage = null;
        try {
            //创建要发送的信息
            mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            String username = null;
            //设置 谁发送的
            helper.setFrom(new InternetAddress(username, dName, "UTF-8"));
            //发给谁 【接收者的邮箱】
            helper.setTo(email);
            //标题内容
            title = titleWithName ? title + "-" + dName : title;
            //发送邮件的辩题
            helper.setSubject(title);
            //发送的内容 是否为html
            helper.setText(content, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送 注册时的验证码
     *
     * @param email 接收者的邮箱【注册人】
     * @param code  验证码
     */
    public void sendRegisterCode(final String email, String code) {
        //实例化一个StringBuffer
        final StringBuffer sb = new StringBuffer();
        sb.append("<h2>").append(email).append(",您好！<h2>").append("<p " +
                "style='color:red'>此次注册的验证码是：").append(code).append("</p>");
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendNormalEmail("验证码", true, sb.toString(), true, email);
            }
        }).start();
    }


    /**
     * 验证邮箱
     */
    public boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(email);
        return m.matches();
    }

}
