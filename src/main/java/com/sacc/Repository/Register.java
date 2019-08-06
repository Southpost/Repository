package com.sacc.Repository;

import com.sacc.Model.RegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 追梦
 */
@Component
public class Register {
    @Autowired
    private MongoTemplate mongo;

//    public Register getUser(int id) {
//        Query query = new Query(Criteria.where("id").is(id));
//        return (Register) mongo.find(query, Register.class);
//    }


    public List<Register> hasRegister(String username) {
        Query query = new Query(Criteria.where("userName").is(username));
        return mongo.find(query, Register.class);
    }

    public void register(String studentNumber, String userName, String email) {
        Query query = new Query(Criteria.where("userName").is(userName));
        Register result = (Register) mongo.find(query, Register.class);
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


    /**
     *  验证邮箱
     */
    public  boolean isEmail(String email) {
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
