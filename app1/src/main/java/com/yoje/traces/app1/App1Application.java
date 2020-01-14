package com.yoje.traces.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author yoje
 * Date    2020-01-08
 */
@SpringBootApplication(scanBasePackages = "com.yoje.traces.app1", exclude = {DataSourceAutoConfiguration.class})
public class App1Application {

    public static void main(String[] args) {
        SpringApplication.run(App1Application.class, args);
    }
}
