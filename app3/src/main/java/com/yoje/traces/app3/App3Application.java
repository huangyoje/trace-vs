package com.yoje.traces.app3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * @author yoje
 * Date    2020-01-08
 */
@EnableKafka
@SpringBootApplication(scanBasePackages = "com.yoje.traces.app3")
public class App3Application {

    public static void main(String[] args) {
        SpringApplication.run(App3Application.class, args);
    }

}
