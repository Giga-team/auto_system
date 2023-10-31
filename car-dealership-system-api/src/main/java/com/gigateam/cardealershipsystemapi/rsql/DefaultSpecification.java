package com.gigateam.cardealershipsystemapi.rsql;

import com.gigateam.cardealershipsystemapi.rsql.converter.RsqlConverter;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class DefaultSpecification<T> implements Specification<T> {
  private static final String NULL_LITERAL = "null";
  private final Map<Class<?>, RsqlConverter<?>> converters;
  private final String property;
  private final ComparisonOperator operator;
  private final List<String> arguments;
  private final Class<T> entityType;

  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    List<Object> args = castArguments(root);
    Object argument = args.get(0);

    switch (RsqlSearchOperation.getSimpleOperator(operator)) {
      case EQUAL -> {
        if (isString(argument)) {
          return criteriaBuilder.like(root.get(property), argument.toString().replace('*', '%'));
        } else if (Objects.isNull(argument)) {
          return criteriaBuilder.isNull(root.get(property));
        } else {
          return criteriaBuilder.equal(root.get(property), argument);
        }
      }

      case NOT_EQUAL -> {
        if (isString(argument)) {
          return criteriaBuilder.notLike(root.get(property), argument.toString().replace('*', '%'));
        } else if (Objects.isNull(argument)) {
          return criteriaBuilder.isNotNull(root.get(property));
        } else {
          return criteriaBuilder.notEqual(root.get(property), argument);
        }
      }

      case GREATER_THAN -> {
        return criteriaBuilder.greaterThan(root.get(property), (Comparable) argument);
      }

      case GREATER_THAN_OR_EQUAL -> {
        return criteriaBuilder.greaterThanOrEqualTo(root.get(property), (Comparable) argument);
      }

      case LESS_THAN -> {
        return criteriaBuilder.lessThan(root.get(property), (Comparable) argument);
      }

      case LESS_THAN_OR_EQUAL -> {
        return criteriaBuilder.lessThanOrEqualTo(root.get(property), (Comparable) argument);
      }

      case IN -> {
        return root.get(property).in(args);
      }

      case NOT_IN -> {
        return criteriaBuilder.not(root.get(property).in(args));
      }
    }

    return null;
  }

  private List<Object> castArguments(Root<T> root) {
    Class<?> propertyType = root.get(property).getJavaType();
    RsqlConverter<?> converter = converters.get(propertyType);

    return arguments.stream()
        .map(arg -> Objects.nonNull(converter) ? converter.convert(arg) : arg)
        .collect(Collectors.toList());
  }

  private boolean isString(Object obj) {
    return obj instanceof String && !NULL_LITERAL.equals(obj);
  }

}
