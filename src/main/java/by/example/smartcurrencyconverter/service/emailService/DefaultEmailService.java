package by.example.smartcurrencyconverter.service.emailService;

import by.example.smartcurrencyconverter.dto.userDTO.RegistrationUserDTO;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultEmailService implements EmailService{

private JavaMailSender emailSender;
private RegistrationUserDTO registrationUserDTO;

@Value("${spring.mail.username}")
private String sender;

//    @Override
//    public void sendSimpleEmail(SimpleEmailDTO simpleEmailDTO) throws MessagingException {
//
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom(sender);
//        simpleMailMessage.setTo(simpleEmailDTO.getRecipient().getEmail());
//        simpleMailMessage.setSubject("Welcome, " + simpleEmailDTO.getRecipient().getName());
//
//        String text = "<!doctype html>\n" +
//                      "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
//                      "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
//                      "<head>\n" +
//                      "    <meta charset=\"UTF-8\">\n" +
//                      "    <meta name=\"viewport\"\n" +
//                      "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
//                      "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
//                      "    <title>Email</title>\n" +
//                      "</head>\n" +
//                      "<body>\n" +
//                      "<div>Welcome, <b>" + simpleEmailDTO.getRecipient().getName() + "</b></div>\n" +
//                      "\n" +
//                      "<div> Your username is <b>" + simpleEmailDTO.getRecipient().getUsername() + "</b></div>\n" +
//                      "<div> Your password is <b>" + simpleEmailDTO.getRecipient().getPassword() + "</b></div>\n" +
//                      "\n" +
//                      "</body>\n" +
//                      "</html>\n";
//
//        simpleMailMessage.setText(text);
//        emailSender.send(simpleMailMessage);
//
//    }


    @Override
    public void sendSimpleEmail(RegistrationUserDTO registrationUserDTO) throws MessagingException {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(registrationUserDTO.getEmail());
        simpleMailMessage.setSubject("Welcome, " + registrationUserDTO.getName());

        String text = "Welcome, " + registrationUserDTO.getName() + "\n\n" +
                      "Your username is " + registrationUserDTO.getUsername() + "\n" +
                      "Your password is " + registrationUserDTO.getPassword() + "\n";

        simpleMailMessage.setText(text);
        emailSender.send(simpleMailMessage);

    }
}
