package com.kanguan.other;

import com.kanguan.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author ZSS
 * @date 2020/3/17 20:26
 * @description 邮箱测试
 */
public class MailTest extends BaseTest {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Test
    public void sendMailTest() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("邮箱发送测试");
        helper.setText("大家，好！<br> &nbsp;&nbsp;<b style='color:red'>今晚7:30在教学楼201开班委会，请各位班委准时参加！</b> <br>谢谢！", true);
        helper.setTo("1271130458@qq.com");
        helper.setFrom("1271130458@qq.com");

        javaMailSender.send(mimeMessage);
    }

}
