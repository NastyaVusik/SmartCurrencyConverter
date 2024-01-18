package by.example.smartcurrencyconverter.service.emailService;

import by.example.smartcurrencyconverter.dto.emailDTO.SimpleEmailDTO;
import by.example.smartcurrencyconverter.dto.userDTO.RegistrationUserDTO;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendSimpleEmail(RegistrationUserDTO registrationUserDTO) throws MessagingException;

}
