package com.gigateam.cardealershipsystemapi.rsql.conterter;

public interface RsqlConverter<T> {

  Class<T> getType();

  T convert(String value);

}
