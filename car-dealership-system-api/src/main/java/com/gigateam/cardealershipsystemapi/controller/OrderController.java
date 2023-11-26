package com.gigateam.cardealershipsystemapi.controller;

import com.gigateam.cardealershipsystemapi.common.dto.ApiResponse;
import com.gigateam.cardealershipsystemapi.common.dto.Responses;
import com.gigateam.cardealershipsystemapi.common.dto.car.CarDto;
import com.gigateam.cardealershipsystemapi.common.dto.order.CreateOrderRequest;
import com.gigateam.cardealershipsystemapi.common.dto.order.FullOrderDto;
import com.gigateam.cardealershipsystemapi.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController extends AbstractController {

  private final OrderService orderService;

  @PostMapping("/orders")
  @Operation(
      tags = {"ORDER"},
      summary = "Endpoint to create order",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(schema = @Schema(implementation = CreateOrderRequest.class))}),
      responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ApiResponse<Long> createOrder(@RequestBody CreateOrderRequest request) {
    log.info("Request on creating order: Request: {}", request);

    return Responses.ok(orderService.createOrder(request.getCarId(), request.getUserId()));
  }

  @GetMapping("/orders/{id}")
  @Operation(
      tags = {"ORDER"},
      summary = "Endpoint to retrieve order by id",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(schema = @Schema(implementation = FullOrderDto.class))}),
      responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ResponseEntity<ApiResponse<FullOrderDto>> getOrderById(@PathVariable("id") Long id) {
    log.info("Request on retrieving order by id. Id: {}", id);

    return orderService.getOrderById(id)
        .map(dto -> new ResponseEntity<>(Responses.ok(dto), HttpStatus.OK))
        .orElseGet(() ->
            new ResponseEntity<>(
                Responses.notFound(String.format(ResponseMessages.ORDER_BY_ID_NOT_FOUND_FORMAT, id)),
                HttpStatus.NOT_FOUND
            )
        );
  }

  @GetMapping("/orders/page")
  @Operation(
      tags = {"ORDER"},
      summary = "Endpoint to retrieve orders by rsql query",
      responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ApiResponse<List<FullOrderDto>> getOrdersPage(
      @RequestParam(value = "query", defaultValue = "") String query,
      @RequestParam("page") Optional<Integer> pageParameter,
      @RequestParam("limit") Optional<Integer> limitParameter
  ) {
    int page = pageParameter.orElse(DEFAULT_PAGE_PARAMETER);
    int limit = limitParameter.orElse(DEFAULT_LIMIT_PARAMETER);

    log.info("Request on retrieving cars page. Query: {}, page: {}, limit: {}", query, page, limit);

    return Responses.ok(orderService.getOrdersPage(query, page, limit));
  }

}