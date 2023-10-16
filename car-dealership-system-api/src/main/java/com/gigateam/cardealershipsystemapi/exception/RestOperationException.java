package com.gigateam.cardealershipsystemapi.exception;

import lombok.Getter;

@Getter
public class RestOperationException extends RuntimeException {

  protected final int responseCode;

  public RestOperationException(String message, int responseCode) {
    super(message);
    this.responseCode = responseCode;
  }

}
