package com.gigateam.cardealershipsystemapi.rsql;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class DefaultRsqlVisitor implements RSQLVisitor<Specification<?>, Class<?>> {

  private final DefaultSpecificationBuilder criteriaBuilder;

  @Override
  public Specification<?> visit(AndNode node, Class<?> param) {
    return criteriaBuilder.createSpecification(node, param);
  }

  @Override
  public Specification<?> visit(OrNode node, Class<?> param) {
    return criteriaBuilder.createSpecification(node, param);
  }

  @Override
  public Specification<?> visit(ComparisonNode node, Class<?> param) {
    return criteriaBuilder.createSpecification(node, param);
  }

}
