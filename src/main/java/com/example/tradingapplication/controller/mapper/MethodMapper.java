package com.example.tradingapplication.controller.mapper;

import com.example.tradingapplication.dto.MethodDTO;
import com.example.tradingapplication.model.Method;

public class MethodMapper {
    private MethodMapper(){}
    public static Method toEntity (MethodDTO methodDTO) {
        return Method.builder().order(methodDTO.getOrder()).params(methodDTO.getParams()).name(methodDTO.getName()).build();
    }
}
