package com.in28minutes.springboot.microservice.example.forex.springbootmicroserviceforexservice.repo;

import com.in28minutes.springboot.microservice.example.forex.springbootmicroserviceforexservice.entity.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    ExchangeValue findByFromAndTo(String from, String to);

    Optional<ExchangeValue> findById(Long id);
}