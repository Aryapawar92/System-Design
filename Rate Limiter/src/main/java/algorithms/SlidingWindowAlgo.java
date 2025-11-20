package main.java.algorithms;

import main.java.interfaces.RateLimiter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowAlgo implements RateLimiter {
    private final int limit;
    private final long windowSizeMs;
    private final ConcurrentHashMap<String, Queue<Long>> mapp = new ConcurrentHashMap<>();

    public SlidingWindowAlgo(int limit,long windowSizeMs){
        this.limit = limit;
        this.windowSizeMs = windowSizeMs;
    }

    @Override
    public boolean allowRequest(String key){
        long now = System.currentTimeMillis();
        mapp.put(key,new LinkedList<>());

        Queue<Long> timestamps = mapp.get(key);

        while (!timestamps.isEmpty() && now - timestamps.peek() > windowSizeMs) {
            timestamps.poll();
        }

        if (timestamps.size() < limit) {
            timestamps.offer(now);
            return true;
        }

        return false;
    }
}
