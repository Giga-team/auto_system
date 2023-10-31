package com.gigateam.cardealershipsystemapi.config;

import com.gigateam.cardealershipsystemapi.common.repository.impl.SearchRepositoryImpl;
import com.gigateam.cardealershipsystemapi.rsql.SpecificationGenerator;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class SearchRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID> extends JpaRepositoryFactoryBean<R, T, ID> {

  private final SpecificationGenerator specificationGenerator;

  public SearchRepositoryFactoryBean(Class<? extends R> repositoryInterface, SpecificationGenerator specificationGenerator) {
    super(repositoryInterface);
    this.specificationGenerator = specificationGenerator;
  }

  @Override
  protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
    return new SearchRepositoryFactory(entityManager, specificationGenerator);
  }

  private static class SearchRepositoryFactory<T, ID> extends JpaRepositoryFactory {
    private final SpecificationGenerator specificationGenerator;

    public SearchRepositoryFactory(EntityManager entityManager, SpecificationGenerator specificationGenerator) {
      super(entityManager);
      this.specificationGenerator = specificationGenerator;
    }

    @Override
    protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
      JpaEntityInformation<T, ?> entityInformation
          = (JpaEntityInformation<T, ?>) getEntityInformation(information.getDomainType());

      return new SearchRepositoryImpl<>(entityInformation, entityManager, specificationGenerator);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
      return SearchRepositoryImpl.class;
    }
  }

}
