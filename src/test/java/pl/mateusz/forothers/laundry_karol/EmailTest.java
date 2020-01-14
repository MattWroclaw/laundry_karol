package pl.mateusz.forothers.laundry_karol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mateusz.forothers.laundry_karol.utils.EmailSender;

import javax.mail.MessagingException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailTest {

    @Autowired
    private EmailSender emailSender;

    @Test
    public void www_site_Mail_Sender(){
        emailSender.sendEmail_site();
    }

    @Test
    public void rest_Mail_Sender() throws MessagingException {
        emailSender.sendEmail("whiteelk@wp.pl", "wygrałeś", "lotto", true);
    }

}
