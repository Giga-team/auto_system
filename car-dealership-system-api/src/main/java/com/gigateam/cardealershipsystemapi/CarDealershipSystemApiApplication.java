package com.gigateam.cardealershipsystemapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class CarDealershipSystemApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CarDealershipSystemApiApplication.class, args);
  }

  @GetMapping("/hello")
  @Operation(
      tags = {"HELLO"},
      summary = "Endpoint to retrieve hello",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "All ok"
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Absent, but ok"
          )
      }
  )
  public String hello() {
    log.info("Request on retrieving hello value");
    return "hello";
  }

}
