package com.gigateam.cardealershipsystemapi.common.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SearchRepository<T, ID> extends JpaRepository<T, ID> {

  List<T> findAllByRsqlQuery(String query);

//  List<T> findAll(String query, Pageable pageable);

}
