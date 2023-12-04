package com.gigateam.cardealershipsystemapi.controller;

import com.gigateam.cardealershipsystemapi.common.dto.ApiResponse;
import com.gigateam.cardealershipsystemapi.common.dto.Responses;
import com.gigateam.cardealershipsystemapi.exception.RestOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public abstract class AbstractController {

  protected static final int DEFAULT_PAGE_PARAMETER = 0;
  protected static final int DEFAULT_LIMIT_PARAMETER = 10;
  private static final String SOMETHING_WENT_WRONG = "Something went wrong";
  private static final String ACCESS_DENIED = "Access denied";


  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
    log.error("Was caught an unhandled exception:", e);

    return new ResponseEntity<>(Responses.internalServerError(SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(RestOperationException.class)
  public ResponseEntity<ApiResponse<Void>> handleRestOperationException(RestOperationException e) {
    log.error("Handling exception:", e);

    return new ResponseEntity<>(Responses.of(e), HttpStatus.valueOf(e.getResponseCode()));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiResponse<Void>> handleAccessDeniedException() {
    log.error("Handling access denied exception");

    return new ResponseEntity<>(Responses.forbidden(ACCESS_DENIED), HttpStatus.FORBIDDEN);
  }

}
