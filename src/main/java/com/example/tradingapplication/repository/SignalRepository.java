package com.example.tradingapplication.repository;

import com.example.tradingapplication.model.Signal;
import org.springframework.data.mongodb.repository.MongoRepository;

interface SignalRepository extends MongoRepository<Signal, Integer> {}
