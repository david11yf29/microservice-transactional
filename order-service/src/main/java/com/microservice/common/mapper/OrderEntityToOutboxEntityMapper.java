package com.microservice.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.entity.Order;
import com.microservice.entity.Outbox;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderEntityToOutboxEntityMapper {

    public Outbox map(Order order) throws JsonProcessingException {
        return Outbox.builder()
                .aggregateId(order.getId().toString())
                // ObjectMapper: 能够很方便地将Java对象转为 Json 格式的数据
                .payload(new ObjectMapper().writeValueAsString(order))
                .createdAt(new Date())
                .processed(false)
                .build();
    }
}
