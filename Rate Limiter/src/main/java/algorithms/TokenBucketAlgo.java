package main.java.algorithms;

import main.java.interfaces.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketAlgo implements RateLimiter {
    private final int capacity;
    private final double refillRate;
    private final ConcurrentHashMap<String, Bucket> mapp = new ConcurrentHashMap<>();

    public TokenBucketAlgo(int capacity,double refillRate){
        this.capacity = capacity;
        this.refillRate = refillRate;
    }

    public static class Bucket{
        double tokens;
        long lastRefillTime;

        Bucket(double tokens,long lastRefillTime){
            this.tokens = tokens;
            this.lastRefillTime = lastRefillTime;
        }
    }

    @Override
    public boolean allowRequest(String key){
        long now = System.currentTimeMillis();
        mapp.put(key,new Bucket(capacity,now));

        Bucket bucket = mapp.get(key);

        double tokensToAdd = (now - bucket.lastRefillTime) / 1000.0 * refillRate;
        bucket.tokens = Math.min(capacity, bucket.tokens + tokensToAdd);
        bucket.lastRefillTime = now;

        if (bucket.tokens >= 1) {
            bucket.tokens -= 1;
            return true;
        }
        return false;

    }
}
