package xin.redips.urls.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
    private static final String HOST = "smtp.aliyun.com";
    //private static final Integer PORT =  25;
    private static final String USERNAME = "redips.xin@aliyun.com";
    private static final String PASSWORD = "aliyun..0000";
    private static final String EMAILFORM = "redips.xin@aliyun.com";
    private static JavaMailSenderImpl mailSender = createMailSender();
   
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        //sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "10000");
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.setProperty("mail.smtp.socketFactory.port", "465");
        p.setProperty("mail.smtp.port", "465");
        sender.setJavaMailProperties(p);
        return sender;
    }

    public static void sendHtmlMail(String to, String subject, String html) throws MessagingException,UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(EMAILFORM, "www.redips.xin");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
    }

    public static void sendHtmlMailBatch(Map<String, String> mailMap) throws MessagingException{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(EMAILFORM);
        
        for(Map.Entry<String, String> entry : mailMap.entrySet()){
        		messageHelper.setTo(entry.getKey());
        		messageHelper.setText(entry.getValue());
        		mailSender.send(mimeMessage);
        }
    }
}