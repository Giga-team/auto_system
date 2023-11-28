package com.gigateam.cardealershipsystemapi.common.dto;

import com.gigateam.cardealershipsystemapi.exception.RestOperationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Responses {

  public static <T> ApiResponse<T> of(int code, String message, T body) {
    return new ApiResponse<>(code, message, body);
  }

  public static <T> ApiResponse<T> of(int code, T body) {
    return of(code, "", body);
  }

  public static <T> ApiResponse<T> of(int code, String message) {
    return of(code, message, null);
  }

  public static <T> ApiResponse<T> of(RestOperationException e) {
    return of(e.getResponseCode(), e.getMessage(), null);
  }

  public static <T> ApiResponse<T> of(int code) {
    return of(code, "", null);
  }

  public static <T> ApiResponse<T> ok(T body) {
    return of(200, body);
  }

  public static <T> ApiResponse<T> ok() {
    return of(200, null);
  }

  public static <T> ApiResponse<T> notFound() {
    return of(404);
  }

  public static <T> ApiResponse<T> notFound(String message) {
    return of(404, message, null);
  }

  public static <T> ApiResponse<T> created(T body) {
    return of(201, body);
  }

  public static <T> ApiResponse<T> created() {
    return of(201, null);
  }

  public static <T> ApiResponse<T> noContent() {
    return of(204);
  }

  public static <T> ApiResponse<T> internalServerError(String message) {
    return of(500, message);
  }

  public static <T> ApiResponse<T> forbidden(String message) {
    return of(403, message);
  }

}
