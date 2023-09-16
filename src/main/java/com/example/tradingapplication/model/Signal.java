package com.example.tradingapplication.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Builder
@Document(collection = "signals")
public class Signal {
    @Id
    private Integer id;
    private List<Method> methods;
}
