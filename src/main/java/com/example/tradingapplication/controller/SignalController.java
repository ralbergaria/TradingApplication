package com.example.tradingapplication.controller;

import com.example.tradingapplication.controller.mapper.SignalMapper;
import com.example.tradingapplication.dto.SignalDTO;
import com.example.tradingapplication.service.SignalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Validated
@RequestMapping("/api/v1/signal")
@RequiredArgsConstructor
@Controller
public class SignalController {
    private final SignalService signalService;

    @PostMapping(value = "/process")
    @Operation(summary = "API to start the signal process")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<String> processSignal(@NotNull @RequestBody Integer signal) {
        signalService.handleSignal(signal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create the new signals, with this API is not necessary to deploy new versions to create new signals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<String> createSignal(@RequestBody @NotEmpty List<@Valid SignalDTO> signalDTOList) {
        signalService.createSignals(signalDTOList.stream().map(SignalMapper::toEntity).toList());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
