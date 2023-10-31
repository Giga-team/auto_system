package com.gigateam.cardealershipsystemapi.config;

import com.gigateam.cardealershipsystemapi.rsql.DefaultRsqlVisitor;
import com.gigateam.cardealershipsystemapi.rsql.DefaultSpecificationBuilder;
import com.gigateam.cardealershipsystemapi.rsql.SpecificationGenerator;
import com.gigateam.cardealershipsystemapi.rsql.conterter.RsqlConverter;
import cz.jirutka.rsql.parser.RSQLParser;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RsqlConfig {

  @Bean
  public RSQLParser rsqlParser() {
    return new RSQLParser();
  }

  @Bean
  public DefaultRsqlVisitor defaultRsqlVisitor(List<RsqlConverter<?>> converters) {
    return new DefaultRsqlVisitor(new DefaultSpecificationBuilder(converters));
  }

  @Bean
  public SpecificationGenerator specificationGenerator(RSQLParser rsqlParser, DefaultRsqlVisitor defaultRsqlVisitor) {
    return new SpecificationGenerator(rsqlParser, defaultRsqlVisitor);
  }

}
