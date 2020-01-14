package com.yoje.traces.app1;

import com.twitter.util.Duration;
import com.twitter.util.Future;
import com.yoje.traces.vs.service.AlphaService;
import com.yoje.traces.vs.service.FinagleRequest;
import com.yoje.traces.vs.service.FinagleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yoje
 * Date    2020-01-08
 */
@RestController
public class TestController {

    @Autowired
    private AlphaService.FutureIface alphaService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/echo")
    public Object echo(String param) {
        try {
            Future<FinagleResponse> resp = alphaService.invoke(new FinagleRequest.Builder().param(param).build());
            return resp.apply(Duration.fromMilliseconds(5 * 1000)).getResp();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/current-mysql-time")
    public Object currentMysqlTime(String param) {
        try {
            List<Object> result = new ArrayList<>();
            jdbcTemplate.query("select current_time", rs -> {
                result.add(rs.getString("current_time"));
            });
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}


