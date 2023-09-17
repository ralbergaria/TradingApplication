package com.example.tradingapplication.controller.mapper;

import com.example.tradingapplication.dto.SignalDTO;
import com.example.tradingapplication.model.Signal;

public class SignalMapper {
    private SignalMapper(){}
    public static Signal toEntity(SignalDTO signalDTO){
        return Signal.builder().id(signalDTO.getId()).
        methods(signalDTO.getMethods().stream().map(MethodMapper::toEntity).toList()).build();
    }
}
