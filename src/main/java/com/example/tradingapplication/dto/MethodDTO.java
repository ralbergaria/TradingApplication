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
public class MethodDTO {
    @NotNull(message = "Method order can not be null")
    private Integer order;
    @NotEmpty(message = "Method name can not be empty")
    private String name;
    private List<Integer> params;
}