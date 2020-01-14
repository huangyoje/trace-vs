package com.yoje.traces.app2;

import com.twitter.util.Future;
import com.yoje.traces.vs.service.AlphaService;
import com.yoje.traces.vs.service.BetaService;
import com.yoje.traces.vs.service.FinagleRequest;
import com.yoje.traces.vs.service.FinagleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yoje
 * Date    2020-01-08
 */
@Service
public class AlphaServiceImpl implements AlphaService.FutureIface {

    Logger logger = LoggerFactory.getLogger(AlphaServiceImpl.class);

    @Resource
    BetaService.FutureIface betaService;

    @Override
    public Future<FinagleResponse> invoke(FinagleRequest request) {
        logger.info("receive request {}", request);
        String req = String.format("%s -> AlphaServer", request.getParam());
        return betaService.invoke(new FinagleRequest.Builder().param(req).build());
    }
}
