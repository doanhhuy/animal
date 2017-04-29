package com.web.mail;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by duyle on 13/02/2017.
 */

public class MailService {

    private MailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmailResetPassword(String to, int key) {

        SimpleMailMessage mail = new SimpleMailMessage();
        String message = "Mã xác minh của bạn là: \"" + key + " \"!\nTruy cập website để cập nhật mật khẩu: http://localhost:8082/updatepassword/" + to;
        mail.setTo(to);
        mail.setSubject("Reset Password");
        mail.setText(String.format(
                simpleMailMessage.getText(), message));
        mailSender.send(mail);
    }

    public void sendEmailRegister(String to) {
        SimpleMailMessage mail = new SimpleMailMessage();
        String message = "Bấm vào link bên dưới để kích hoạt tài khoản!\nhttp://localhost:8082/activation/" + to;
        mail.setTo(to);
        mail.setSubject("Activation Account");
        mail.setText(String.format(
                simpleMailMessage.getText(), message));
        mailSender.send(mail);
    }
}
