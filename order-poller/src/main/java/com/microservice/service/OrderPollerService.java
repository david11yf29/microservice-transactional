package com.microservice.service;

import com.microservice.entity.Outbox;
import com.microservice.publisher.MessagePublisher;
import com.microservice.repository.OutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
@Slf4j
public class OrderPollerService {

    @Autowired
    private OutboxRepository outboxRepository;

    @Autowired
    private MessagePublisher messagePublisher;

    @Scheduled(fixedRate = 60000) // 1min
    public void pollOutboxMessagesAndPublish() {
        // 1. TODO fetch unprocessed record
        List<Outbox> unprocessedRecords = outboxRepository.findByprocessedFalse();
        log.info("unprocessed records count : {}", unprocessedRecords.size());

        // 2. TODO Publish record to kafka/queue
        unprocessedRecords.forEach(outbox -> {
            try {
                messagePublisher.publish(outbox.getPayload());
                // update the message status to processed=true to avoid duplicate message processing
                outbox.setProcessed(true);
                outboxRepository.save(outbox);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        });

    }

}
