package com.microservice.service;

import com.microservice.common.dto.OrderRequestDTO;
import com.microservice.common.mapper.OrderDTOtoEntityMapper;
import com.microservice.entity.Order;
import com.microservice.repository.OrderRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = orderDTOtoEntityMapper.map(orderRequestDTO);
        order = orderRepository.save(order);

        return null;
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
