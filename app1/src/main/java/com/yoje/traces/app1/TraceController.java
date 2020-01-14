package com.yoje.traces.app1;

import com.twitter.util.Duration;
import com.twitter.util.Future;
import com.yoje.traces.vs.service.AlphaService;
import com.yoje.traces.vs.service.FinagleRequest;
import com.yoje.traces.vs.service.FinagleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yoje
 * Date    2020-01-08
 */
@RestController
public class TraceController {

    @Resource
    private AlphaService.FutureIface alphaService;

    @GetMapping("/echo")
    public Object invoke(String param) {
        try {
            Future<FinagleResponse> resp = alphaService.invoke(new FinagleRequest.Builder().param(param).build());
            return resp.apply(Duration.fromMilliseconds(5 * 1000)).getResp();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}


