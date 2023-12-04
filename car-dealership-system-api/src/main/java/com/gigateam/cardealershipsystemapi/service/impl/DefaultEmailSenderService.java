package com.gigateam.cardealershipsystemapi.service.impl;

import com.gigateam.cardealershipsystemapi.common.dto.car.CarDto;
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

  @Override
  public void sendDetailsEmail(String toEmail, CarDto car) {
    String subject = "Your Car Details";
    String body = "Hello!\n\nHere are the details of the car:\n\n" +
            "ID: " + car.getId() + "\n" +
            "Article: " + car.getArticle() + "\n" +
            "Brand: " + car.getBrand() + "\n" +
            "Model: " + car.getModel() + "\n" +
            "Engine Capacity: " + car.getEngineCapacity() + "\n" +
            "Power: " + car.getPower() + "\n" +
            "Number of Seats: " + car.getNumberOfSeats() + "\n" +
            "Gearbox: " + car.getGearBox() + "\n" +
            "Price: " + car.getPrice() + "\n" +
            "Thank you for considering our services!";

    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("TechnoDrift");
    message.setTo(toEmail);
    message.setText(body);
    message.setSubject(subject);
    mailSender.send(message);

    System.out.println("Email sent to: " + toEmail);
  }


}
