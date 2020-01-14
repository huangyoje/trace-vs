package com.yoje.traces.app2;

import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Thrift;
import com.twitter.finagle.thrift.Protocols;
import com.twitter.util.Duration;
import com.yoje.traces.vs.service.AlphaService;
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
    private AlphaService.FutureIface alphaService;

    @Bean
    public ListeningServer rpcServer() {

        AlphaService.FinagledService finagledService =
                new AlphaService.FinagledService(alphaService,
                        Protocols.binaryFactory(
                                Protocols.binaryFactory$default$1(),
                                Protocols.binaryFactory$default$2(),
                                Protocols.binaryFactory$default$3(),
                                Protocols.binaryFactory$default$4()));

        ListeningServer server = Thrift.server()
                .withLabel("alpha")
                .withRequestTimeout(Duration.fromSeconds(30))
                .serve(new InetSocketAddress(10003), finagledService);
        // fail fast
        server.announce("zk!localhost:2181!/service/traces/alpha!0").get();
        return server;
    }

}
