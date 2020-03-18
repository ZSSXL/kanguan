package com.kanguan.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author ZSS
 * @date 2020/3/17 21:09
 * @description 邮箱发送工具
 */
@Component
public class EmailUtil {

    private static JavaMailSenderImpl javaMailSender;

    private final JavaMailSenderImpl mailSender;

    @Autowired
    public EmailUtil(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @PostConstruct
    public void beforeInit() {
        javaMailSender = mailSender;
    }

    /**
     * 发送邮箱验证
     */
    public static void sendVerifyCode(String registerEmail, String verifyCode) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("看官网邮箱验证");
        helper.setText("欢迎使用<b>看官网</b> <br>您的注册验证码是: <b style='color:red'>" + verifyCode + "</b> 该验证码3分钟失效！ <br>请勿回复该邮箱，谢谢配合, 希望有您想要的资源！！！", true);
        helper.setTo(registerEmail);
        helper.setFrom("1271130458@qq.com");
        javaMailSender.send(mimeMessage);
    }

    /**
     * 判断字符串是否为邮箱
     *
     * @param str 字符串
     * @return Boolean
     */
    public static Boolean isEmail(String str) {
        boolean isEmail = false;
        String expr = "^([a-zA-Z0-9_\\-.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";

        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }
}
