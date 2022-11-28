package com.bridgelabz.bookstore.service;
import com.bridgelabz.bookstore.model.Email;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailService implements IEmailService{
    @Override
    public void sendMail(Email email) {
        final String fromEmail = "sahilpawar1004@gmail.com";
        final String fromPwd = "yckohdhpbcbyaxbt";
        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.protocols","TLSv1.2");
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail,fromPwd);
            }
        };
        Session session = Session.getInstance(properties,authenticator);
        MimeMessage mail = new MimeMessage(session);
        try {
            mail.addHeader("Content-type", "text/HTML; charset=UTF-8");
            mail.addHeader("format", "flowed");
            mail.addHeader("Content-Transfer-Encoding", "8bit");

            mail.setFrom(new InternetAddress(fromEmail));
            mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            mail.setText(email.getBody(), "UTF-8");
            mail.setSubject(email.getSubject(), "UTF-8");
            Transport.send(mail);
            System.out.println("sent email");
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
