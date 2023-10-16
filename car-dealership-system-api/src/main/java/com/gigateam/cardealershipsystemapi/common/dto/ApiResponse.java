package com.gigateam.cardealershipsystemapi.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

  private final int responseCode;
  private String message;
  private T body;

}
