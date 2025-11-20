package algorithms;

import interfaces.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class LeakyBucketAlgo implements RateLimiter {
    private final int limit;
    private final double leakRate;

    private final ConcurrentHashMap<String , Bucket> mapp = new ConcurrentHashMap<>();

    public static class Bucket{
        double water;
        long lastLeakTime;
        Bucket(double water, long lastLeakTime) {
            this.water = water;
            this.lastLeakTime = lastLeakTime;
        }
    }

    public LeakyBucketAlgo(int limit,double leakRate){
        this.limit = limit;
        this.leakRate = leakRate;
    }
    @Override
    public boolean allowRequest(String key){
        long now = System.currentTimeMillis();
        mapp.put(key,new Bucket(0,now));

        Bucket bucket = mapp.get(key);
        double leaked = (now - bucket.lastLeakTime) / 1000.0 * leakRate;
        bucket.water = Math.max(0, bucket.water - leaked);
        bucket.lastLeakTime = now;

        if (bucket.water < limit) {
            bucket.water++;
            return true;
        }

        return false;
    }
}
