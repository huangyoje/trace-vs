package com.yoje.traces.app2;

import com.twitter.finagle.Thrift;
import com.twitter.util.Duration;
import com.yoje.traces.vs.service.BetaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yoje
 * Date    2020-01-08
 */
@Configuration
public class ClientConfiguration {

    @Bean
    public BetaService.FutureIface contractService() {
        return Thrift.client()
                .withLabel("betaService_website")
                .withSessionPool().minSize(10)
                .withSessionPool().maxSize(50)
                .withSessionPool().maxWaiters(100)
                .withSessionQualifier().noFailFast()
                .withRequestTimeout(Duration.fromSeconds(5))
                .newIface("zk!localhost:2181!/service/traces/beta", "BetaServiceClient",
                        BetaService.FutureIface.class);
    }
}
