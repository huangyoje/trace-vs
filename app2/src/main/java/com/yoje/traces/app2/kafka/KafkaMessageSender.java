package com.yoje.traces.app2.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yoje
 * Date    2020-01-15
 */
@Component
public class KafkaMessageSender {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;

    public void sendMessage(String msg) {
        kafkaTemplate.send(topic, msg);
    }
}
