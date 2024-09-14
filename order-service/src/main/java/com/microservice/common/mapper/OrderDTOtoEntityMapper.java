package com.microservice.common.mapper;

import com.microservice.common.dto.OrderRequestDTO;
import com.microservice.entity.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderDTOtoEntityMapper {

    public Order map(OrderRequestDTO orderRequestDTO) {
        return Order.builder()
                .customerId(orderRequestDTO.getCustomerId())
                .name(orderRequestDTO.getName())
                .productType(orderRequestDTO.getProductType())
                .quantity(orderRequestDTO.getQuantity())
                .price(orderRequestDTO.getPrice())
                .orderDate(new Date())
                .build();
    }

}
