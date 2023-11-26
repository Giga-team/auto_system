package com.gigateam.cardealershipsystemapi.common.repository.impl;

import com.gigateam.cardealershipsystemapi.common.repository.SearchRepository;
import com.gigateam.cardealershipsystemapi.rsql.SpecificationGenerator;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class SearchRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements SearchRepository<T, ID> {

  private static final String ID = "id";

  private SpecificationGenerator specificationGenerator;

  public SearchRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
  }

  public SearchRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager, SpecificationGenerator specificationGenerator) {
    super(entityInformation, entityManager);
    this.specificationGenerator = specificationGenerator;
  }

  @Override
  public List<T> findAll(String query) {
    Specification<T> specification = specificationGenerator.getSpecification(query);

    return super.findAll(specification);
  }

  @Override
  public List<T> findAll(String query, int page, int limit) {
    Specification<T> specification = specificationGenerator.getSpecification(query);

    return super.findAll(specification, createPageable(page, limit)).toList();
  }

  private Pageable createPageable(int page, int limit) {
    return PageRequest.of(page, limit, Sort.by(ID));
  }

}
