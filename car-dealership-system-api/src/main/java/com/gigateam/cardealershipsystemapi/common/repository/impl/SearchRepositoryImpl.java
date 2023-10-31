package com.gigateam.cardealershipsystemapi.common.repository.impl;

import com.gigateam.cardealershipsystemapi.common.repository.SearchRepository;
import com.gigateam.cardealershipsystemapi.rsql.SpecificationGenerator;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class SearchRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements SearchRepository<T, ID> {

  private SpecificationGenerator specificationGenerator;

  public SearchRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
  }

  public SearchRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager, SpecificationGenerator specificationGenerator) {
    super(entityInformation, entityManager);
    this.specificationGenerator = specificationGenerator;
  }

  @Override
  public List<T> findAllByRsqlQuery(String query) {
    Specification<T> specification = specificationGenerator.getSpecification(query);

    return super.findAll(specification);
  }

//  @Override
//  public List<T> findAll(String query, Pageable pageable) {
//    Specification<T> specification = specificationGenerator.getSpecification(query);
//
//    return super.findAll(specification, pageable).toList();
//  }

}
