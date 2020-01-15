package com.yoje.traces.app2.controller;

import com.yoje.traces.app2.kafka.KafkaMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yoje
 * Date    2020-01-15
 */
@RestController
public class TestController {

    @Autowired
    private KafkaMessageSender kafkaMessageSender;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/send-kafka")
    public Object sendKafka(String msg) {
        try {
            List<Object> result = new ArrayList<>();
            jdbcTemplate.query("select current_time", rs -> {
                result.add(rs.getString("current_time"));
            });
            String message = String.format("time=%s, msg=%s", result.get(0), msg);
            kafkaMessageSender.sendMessage(message);
            return message;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
