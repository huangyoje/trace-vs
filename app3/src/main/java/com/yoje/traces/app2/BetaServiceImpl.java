package com.yoje.traces.app2;

import com.twitter.util.Future;
import com.yoje.traces.vs.service.BetaService;
import com.yoje.traces.vs.service.FinagleRequest;
import com.yoje.traces.vs.service.FinagleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author yoje
 * Date    2020-01-08
 */
@Service
public class BetaServiceImpl implements BetaService.FutureIface {

    Logger logger = LoggerFactory.getLogger(BetaServiceImpl.class);

    @Override
    public Future<FinagleResponse> invoke(FinagleRequest request) {
        logger.info("receive request {}", request);

        FinagleResponse resp =
                new FinagleResponse.Builder().resp(String.format("%s -> BetaServer", request.getParam())).build();
        return Future.value(resp);
    }
}
