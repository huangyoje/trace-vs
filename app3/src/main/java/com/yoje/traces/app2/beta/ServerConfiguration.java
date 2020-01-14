package com.yoje.traces.app2.beta;

import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Thrift;
import com.twitter.finagle.thrift.Protocols;
import com.twitter.util.Duration;
import com.yoje.traces.vs.service.BetaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @author yoje
 * Date    2020-01-08
 */
@Configuration
public class ServerConfiguration {

    @Resource
    private BetaService.FutureIface betaService;


    @Bean
    public ListeningServer rpcServer() {

        BetaService.FinagledService finagledService =
                new BetaService.FinagledService(betaService,
                        Protocols.binaryFactory(
                                Protocols.binaryFactory$default$1(),
                                Protocols.binaryFactory$default$2(),
                                Protocols.binaryFactory$default$3(),
                                Protocols.binaryFactory$default$4()));

        ListeningServer server = Thrift.server()
                .withLabel("beta")
                .withRequestTimeout(Duration.fromSeconds(30))
                .serve(new InetSocketAddress(10001), finagledService);
        // fail fast
        server.announce("zk!localhost:2181!/service/traces/beta!0").get();
        return server;
    }

}
