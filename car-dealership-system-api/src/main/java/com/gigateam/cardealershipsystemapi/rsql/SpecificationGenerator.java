package com.gigateam.cardealershipsystemapi.rsql;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class SpecificationGenerator {

  private final RSQLParser rsqlParser;
  private final DefaultRsqlVisitor visitor;

  public <T> Specification<T> getSpecification(String query) {
    if (StringUtils.isEmpty(query)) {
      return Specification.where(null);
    }

    Node root = rsqlParser.parse(query);

    return (Specification<T>) root.accept(visitor);
  }

}
