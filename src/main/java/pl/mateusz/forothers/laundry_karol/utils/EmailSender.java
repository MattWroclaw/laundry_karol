package pl.mateusz.forothers.laundry_karol.utils;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
//@RequiredArgsConstructor
public class EmailSender implements MessageService {

    private   JavaMailSender javaMailSender;

    @Autowired
    public EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public EmailSender() {
    }

    public void sendEmail_site(){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("whiteelk@wp.pl");
        mailMessage.setSubject("Test Karol API");
        mailMessage.setText("Hello world from \n Spring boot");

        javaMailSender.send(mailMessage);
    }

    @Override
    public void sendEmail(String to, String subject, String content, boolean isHtmlContent) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, isHtmlContent);
        javaMailSender.send(mimeMessage);
    }
}
