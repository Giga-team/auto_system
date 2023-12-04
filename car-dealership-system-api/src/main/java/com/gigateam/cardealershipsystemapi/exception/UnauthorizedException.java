package com.gigateam.cardealershipsystemapi.exception;

public class UnauthorizedException extends RestOperationException {

  public UnauthorizedException(String message) {
    super(message, 401);
  }

}