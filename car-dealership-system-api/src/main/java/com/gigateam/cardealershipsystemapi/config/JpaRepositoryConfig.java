package com.gigateam.cardealershipsystemapi.config;

import com.gigateam.cardealershipsystemapi.common.repository.impl.SearchRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    repositoryBaseClass = SearchRepositoryImpl.class,
    repositoryFactoryBeanClass = SearchRepositoryFactoryBean.class,
    basePackages = {"com.gigateam.cardealershipsystemapi"}
)
public class JpaRepositoryConfig {

}
