package com.gigateam.cardealershipsystemapi.repository;

import com.gigateam.cardealershipsystemapi.common.repository.SearchRepository;
import com.gigateam.cardealershipsystemapi.domain.Order;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends SearchRepository<Order, Long> {

  Optional<Order> findByCarId(Long carId);

}
