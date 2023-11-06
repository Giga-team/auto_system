package com.gigateam.cardealershipsystemapi.rsql;

import com.gigateam.cardealershipsystemapi.rsql.converter.RsqlConverter;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.LogicalOperator;
import cz.jirutka.rsql.parser.ast.Node;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;

public class DefaultSpecificationBuilder {

  private final Map<Class<?>, RsqlConverter<?>> converters;

  public DefaultSpecificationBuilder(List<RsqlConverter<?>> converters) {
    this.converters = converters.stream()
        .collect(Collectors.toMap(RsqlConverter::getType, v -> v));
  }

  public <T> Specification<T> createSpecification(Node node, Class<T> entityType) {
    if (node instanceof LogicalNode logicalNode) {
      return createSpecification(logicalNode, entityType);
    }

    if (node instanceof ComparisonNode comparisonNode) {
      return createSpecification(comparisonNode, entityType);
    }

    return null;
  }

  private <T> Specification<T> createSpecification(LogicalNode node, Class<T> entityType) {
    List<Specification<T>> specs = node.getChildren()
        .stream()
        .map(childrenNode -> createSpecification(childrenNode, entityType))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    if (specs.isEmpty()) {
      return null;
    }

    Specification<T> result = specs.get(0);

    if (node.getOperator() == LogicalOperator.AND) {
      for (int i = 1; i < specs.size(); i++) {
        result = Specification.where(result).and(specs.get(i));
      }
    } else if (node.getOperator() == LogicalOperator.OR) {
      for (int i = 1; i < specs.size(); i++) {
        result = Specification.where(result).or(specs.get(i));
      }
    }

    return result;
  }

  private <T> Specification<T> createSpecification(ComparisonNode node, Class<T> entityType) {
    return Specification.where(
        new DefaultSpecification<>(
            converters,
            node.getSelector(),
            node.getOperator(),
            node.getArguments(),
            entityType
        )
    );
  }

}
