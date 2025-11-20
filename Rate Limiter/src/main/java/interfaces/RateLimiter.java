package main.java.interfaces;

public interface RateLimiter {
    boolean allowRequest(String key);
}
