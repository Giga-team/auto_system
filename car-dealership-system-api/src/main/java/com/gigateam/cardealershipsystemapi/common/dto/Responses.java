package com.gigateam.cardealershipsystemapi.common.dto;

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

  public static <T> ApiResponse<T> of(int code) {
    return of(code, "", null);
  }

  public static <T> ApiResponse<T> ok(T body) {
    return of(200, body);
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

  public static <T> ApiResponse<T> noContent() {
    return of(204);
  }

}
