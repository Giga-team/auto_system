package com.gigateam.cardealershipsystemapi.domain;

public enum OrderStatus {

  CREATED,
  IN_PROGRESS,
  IN_PLACE,
  COMPLETE,
  CANCELLED;

  public boolean isCancelled() {
    return CANCELLED.equals(this);
  }

}
