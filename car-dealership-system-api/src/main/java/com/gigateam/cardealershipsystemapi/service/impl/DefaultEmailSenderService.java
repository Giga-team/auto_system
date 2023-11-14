package com.gigateam.cardealershipsystemapi.service.impl;

import com.gigateam.cardealershipsystemapi.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultEmailSenderService implements EmailSenderService {

  private final JavaMailSender mailSender;

  @Override
  public void sendSimpleEmail(String toEmail, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("TechnoDrift");
    message.setTo(toEmail);
    message.setText(body);
    message.setSubject(subject);
    mailSender.send(message);

    System.out.println("Mail send to: " + toEmail);
  }

}
