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
    @NotNull
    private Integer order;
    @NotEmpty
    private String name;
    @NotEmpty
    private List<Integer> params;
}