package com.gigateam.cardealershipsystemapi.service;

import com.gigateam.cardealershipsystemapi.common.dto.car.CarDto;

public interface EmailSenderService {

  void sendSimpleEmail(String toEmail, String subject, String body);

  void sendDetailsEmail(String toEmail, CarDto car);

}