package com.yoje.traces.app3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yoje
 * Date    2020-01-15
 */
@Component
public class KafkaMessageHandler {

    Logger logger = LoggerFactory.getLogger(KafkaMessageHandler.class);

    @KafkaListener(topics = "${spring.kafka.topic}")
    public void receiveMessage(@Headers Map<?, ?> headers, @Payload String msg) {
        logger.info("receive kafka message. headers {}, msg {}", headers, msg);
    }
}
