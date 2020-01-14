package pl.mateusz.forothers.laundry_karol.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class MailApi {

    private EmailSender mailService;

    @Autowired
    public MailApi(EmailSender mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/sendMail")
    public String sendMail() throws MessagingException {
        mailService.sendEmail("whiteelk@wp.pl",
                "Wygrałeś",
                "<b>1000 000 zł</b><br>:P", true);
        return "wysłano";
    }

}
