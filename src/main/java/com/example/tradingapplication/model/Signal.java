package com.example.tradingapplication.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "signals")
public class Signal {
    @Id
    private Integer id;
    private SignalProcess signalProcess;
}
