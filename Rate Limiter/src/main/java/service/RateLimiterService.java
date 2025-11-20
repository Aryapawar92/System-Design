package main.java.service;

import main.java.interfaces.RateLimiter;

public class RateLimiterService {
    private final RateLimiter limiter;

    public RateLimiterService(RateLimiter limiter) {
        this.limiter = limiter;
    }

    public boolean isAllowed(String key) {
        return limiter.allowRequest(key);
    }
}
