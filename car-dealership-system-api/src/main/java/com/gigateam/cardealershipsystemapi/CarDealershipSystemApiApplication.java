package com.gigateam.cardealershipsystemapi;

import com.gigateam.cardealershipsystemapi.service.EmailSenderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;


@SpringBootApplication
public class CarDealershipSystemApiApplication {

  @Autowired
  private EmailSenderService senderService;
  public static void main(String[] args) {
    SpringApplication.run(CarDealershipSystemApiApplication.class, args);
  }

}
