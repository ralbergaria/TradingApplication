package com.example.tradingapplication.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Method {
    private Integer order;
    private String name;
    private List<Integer> params;
}
