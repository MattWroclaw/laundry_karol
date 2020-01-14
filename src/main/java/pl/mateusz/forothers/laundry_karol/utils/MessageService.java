package pl.mateusz.forothers.laundry_karol.utils;

import javax.mail.MessagingException;

public interface MessageService {
    void sendEmail(String to, String subject, String content, boolean isHtmlContent) throws MessagingException;
}
