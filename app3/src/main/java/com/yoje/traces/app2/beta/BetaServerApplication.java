package com.yoje.traces.app2.beta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author yoje
 * Date    2020-01-08
 */
@SpringBootApplication(scanBasePackages = "com.yoje.traces.app2.beta", exclude = {DataSourceAutoConfiguration.class})
public class BetaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetaServerApplication.class, args);
    }

}
