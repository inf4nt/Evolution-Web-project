package evolution.service.notification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {


    public void send(String to, String subject, String message) {
        String from = "notification.evolution@gmail.com";

        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mail, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message, true);
            javaMailSender.send(mail);
        } catch (MessagingException exception) {
            exception.printStackTrace();
        }
    }

    @Autowired
    private JavaMailSender javaMailSender;
}
