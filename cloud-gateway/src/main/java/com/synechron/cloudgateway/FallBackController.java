package com.synechron.cloudgateway;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/wordCounterServiceFallback")
    @CircuitBreaker(name = "wordCounter")
    public String wordCounterServiceFallback() {
        return "Unable to reach the service at this moment";
    }
}
