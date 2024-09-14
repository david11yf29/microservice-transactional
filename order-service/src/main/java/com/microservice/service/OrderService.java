package com.microservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.common.dto.OrderRequestDTO;
import com.microservice.common.mapper.OrderDTOtoEntityMapper;
import com.microservice.common.mapper.OrderEntityToOutboxEntityMapper;
import com.microservice.entity.Order;
import com.microservice.entity.Outbox;
import com.microservice.repository.OrderRepository;
import com.microservice.repository.OutboxRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderDTOtoEntityMapper orderDTOtoEntityMapper;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderEntityToOutboxEntityMapper orderEntityToOutboxEntityMapper;
    @Autowired
    private OutboxRepository outboxRepository;

    @Transactional
    public Order createOrder(OrderRequestDTO orderRequestDTO) throws JsonProcessingException {
        Order order = orderDTOtoEntityMapper.map(orderRequestDTO);
        order = orderRepository.save(order);

        Outbox outbox = orderEntityToOutboxEntityMapper.map(order);
        outboxRepository.save(outbox);

        return order;
    };
}

//@Id
//@GeneratedValue(strategy = GenerationType.IDENTITY)
//private Long id;
//private String name;
//private String customerId;
//private String productType;
//private int quantity;
//private BigDecimal price;
//private Date orderDate;
