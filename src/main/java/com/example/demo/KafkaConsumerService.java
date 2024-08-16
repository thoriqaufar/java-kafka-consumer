package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class KafkaConsumerService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final KafkaTemplate kafkaTemplate;

    public KafkaConsumerService(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "transaction", groupId = "java")
    public void listen(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
//        String mockbinUrl = "https://d702a2b011f04e6390b083e529ecccaf.api.mockbin.io/";
        String mockbinUrl = "https://d702a2b011f04e639ad0b083e529ecccaf.api.mockbin.io/";
//        String mockbinUrl = "https://62843350f6494aa285f590f58ed6ef43.api.mockbin.io/";
        try {

            restTemplate.postForObject(mockbinUrl, message, String.class);
        } catch (Exception e) {
//            kafkaTemplate.send(topic, message);
            System.out.println(e.getMessage());
        }

    }
}

