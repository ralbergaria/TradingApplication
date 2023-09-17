package com.example.tradingapplication.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SignalDTO {
    @NotNull
    private Integer id;
    @NotNull
    @NotEmpty
    private List<MethodDTO> methods;
}
