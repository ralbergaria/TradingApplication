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
    @NotNull(message = "Id can not be null")
    private Integer id;
    @NotNull(message = "Methods can not be null")
    @NotEmpty(message = "Methods can not be empty")
    private List<MethodDTO> methods;
}
