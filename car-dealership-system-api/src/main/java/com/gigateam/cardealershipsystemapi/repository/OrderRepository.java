package com.gigateam.cardealershipsystemapi.repository;

import com.gigateam.cardealershipsystemapi.common.repository.SearchRepository;
import com.gigateam.cardealershipsystemapi.domain.CarStatus;
import com.gigateam.cardealershipsystemapi.domain.Order;
import com.gigateam.cardealershipsystemapi.domain.OrderStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends SearchRepository<Order, Long> {

  Optional<Order> findByCarId(Long carId);

  @Modifying
  @Query("UPDATE Order c SET c.status = :status WHERE c.id = :id")
  void setOrderStatus(@Param("id") Long id, @Param("status") OrderStatus status);

}
