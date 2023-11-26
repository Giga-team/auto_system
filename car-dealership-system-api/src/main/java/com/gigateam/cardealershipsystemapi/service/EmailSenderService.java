package com.gigateam.cardealershipsystemapi.service;

public interface EmailSenderService {

  void sendSimpleEmail(String toEmail, String subject, String body);

}