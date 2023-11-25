package com.gigateam.cardealershipsystemapi.controller;

import com.gigateam.cardealershipsystemapi.common.dto.ApiResponse;
import com.gigateam.cardealershipsystemapi.common.dto.Responses;
import com.gigateam.cardealershipsystemapi.common.dto.order.CreateOrderRequest;
import com.gigateam.cardealershipsystemapi.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

}
