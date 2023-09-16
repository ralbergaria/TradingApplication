package com.example.tradingapplication.repository;

import com.example.tradingapplication.model.Signal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SignalRepository extends MongoRepository<Signal, Integer> {}
