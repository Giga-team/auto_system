package com.gigateam.cardealershipsystemapi.controller;

import com.gigateam.cardealershipsystemapi.common.dto.ApiResponse;
import com.gigateam.cardealershipsystemapi.common.dto.Responses;
import com.gigateam.cardealershipsystemapi.common.dto.client.ClientDto;
import com.gigateam.cardealershipsystemapi.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientController {

  private final ClientService clientService;

  @GetMapping("/client/{id}")
  @Operation(
          tags = {"CLIENT"},
          summary = "Endpoint to client by id",
          responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ResponseEntity<ApiResponse<ClientDto>> getClientById(@PathVariable("id") Long id) {
    log.info("Request on retrieving client by id. Id: {}", id);

    return clientService.getClientById(id)
            .map(dto -> new ResponseEntity<>(Responses.ok(dto), HttpStatus.OK))
            .orElseGet(() ->
                    new ResponseEntity<>(
                            Responses.notFound(String.format(ResponseMessages.CLIENT_BY_ID_NOT_FOUND_FORMAT, id)),
                            HttpStatus.NOT_FOUND
                    )
            );
  }

  @PostMapping("/client")
  @Operation(
          tags = {"CLIENT"},
          summary = "Endpoint to create client",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(schema = @Schema(implementation = ClientDto.class))}),
          responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ResponseEntity<ApiResponse<Long>> createClient(@RequestBody ClientDto dto) {
    log.info("Request on creating client. Client: {}", dto);

    Long newId = clientService.createClient(dto);

    return new ResponseEntity<>(Responses.created(newId), HttpStatus.CREATED);
  }

  @PutMapping("/client/{id}")
  @Operation(
          tags = {"CLIENT"},
          summary = "Endpoint to update client",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(schema = @Schema(implementation = ClientDto.class))}),
          responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ApiResponse<Long> updateClient(@PathVariable("id") Long id, @RequestBody ClientDto dto) {
    log.info("Request on updating client. Client id: {}, new data: {}", id, dto);

    Long updatedCarId = clientService.updateClient(id, dto);

    return Responses.ok(updatedCarId);
  }

  @DeleteMapping("/client/{id}")
  @Operation(
          tags = {"CLIENT"},
          summary = "Endpoint to delete client by id",
          responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(useReturnTypeSchema = true)}
  )
  public ResponseEntity<ApiResponse<Void>> deleteClientById(@PathVariable("id") Long id) {
    log.info("Request on deleting client by id. Id: {}", id);

    return clientService.deleteClientById(id)
            ? new ResponseEntity<>(Responses.noContent(), HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(Responses.notFound(), HttpStatus.NOT_FOUND);
  }

}
