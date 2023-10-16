package com.gigateam.cardealershipsystemapi.exception;

public class NotFoundException extends RestOperationException {

  public NotFoundException(String message) {
    super(message, 404);
  }

}
