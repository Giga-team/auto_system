package com.gigateam.cardealershipsystemapi.repository;

import com.gigateam.cardealershipsystemapi.common.repository.SearchRepository;
import com.gigateam.cardealershipsystemapi.domain.VwOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface VwOrderRepository extends SearchRepository<VwOrder, Long> {

}
