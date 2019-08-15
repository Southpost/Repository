package com.sacc;


import org.springframework.boot.SpringApplication;

/**
 * @author 追梦
 */
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

////package com.qcl;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.mail.internet.MimeMessage;
//
///**
// * @author 追梦
// */ /*
// * 追梦
// * 2113132982@qq.com
// **/
//@RunWith(SpringRunner.class)
//public class Application {
//
//
//    public void contextLoads() {
//    }
//
//
//    @Autowired
//    private JavaMailSenderImpl mailSender;
//
//    /**
//     * 发送包含简单文本的邮件
//     */
//
//    public void sendTxtMail() {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        // 设置收件人，寄件人
//        simpleMailMessage.setTo("2113132982@qq.com", "2508225311@qq.com");
//        simpleMailMessage.setFrom("1561182 3564@163.com");
//        simpleMailMessage.setSubject("Spring Boot Mail 邮件【文本】");
//        simpleMailMessage.setText("这里是一段简单文本。");
//        // 发送邮件
//        mailSender.send(simpleMailMessage);
//
//        System.out.println("邮件已发送");
//    }
//
//    /**
//     * 发送包含HTML文本的邮件
//     *
//     * @throws Exception
//     */
//
//    public void sendHtmlMail() throws Exception {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//        mimeMessageHelper.setTo("2501902696@qq.com");
//        mimeMessageHelper.setFrom("15611823564@163.com");
//        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【HTML】");
//
//        // 启用html
//        String sb = "<html><head></head>" +
//                "<body><h1>spring 邮件</h1><p>hello!this is spring mail test。</p></body>" +
//                "</html>";
//        mimeMessageHelper.setText(sb, true);
//        // 发送邮件
//        mailSender.send(mimeMessage);
//
//        System.out.println("邮件已发送");
//
//    }
//
//}

