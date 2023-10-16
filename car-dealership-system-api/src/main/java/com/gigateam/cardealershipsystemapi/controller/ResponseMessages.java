package com.gigateam.cardealershipsystemapi.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseMessages {

  public static final String CAR_BY_ID_NOT_FOUND_FORMAT = "Car with id: %s didn't found";

  public static final String CLIENT_BY_ID_NOT_FOUND_FORMAT = "Client with id: %s didn't found";

}
