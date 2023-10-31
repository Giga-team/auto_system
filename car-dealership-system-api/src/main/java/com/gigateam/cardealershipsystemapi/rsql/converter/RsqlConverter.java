package com.gigateam.cardealershipsystemapi.rsql.converter;

public interface RsqlConverter<T> {

  Class<T> getType();

  T convert(String value);

}
