package main.java.algorithms;

import main.java.interfaces.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class fixedWindowAlgo implements RateLimiter {
    private final int limit;
    private final long windowSizeMs;
    private final ConcurrentHashMap<String, Window> mapp = new ConcurrentHashMap<>();

    public fixedWindowAlgo(int limit,long windowSizeMs){
        this.limit = limit;
        this.windowSizeMs = windowSizeMs;
    }
    @Override
    public boolean allowRequest(String key){
        long now = System.currentTimeMillis();
        mapp.putIfAbsent(key,new Window(0,now));

        Window window = mapp.get(key);

        if (now - window.startTime >= windowSizeMs) {
            window.startTime = now;
            window.count = 0;
        }

        if (window.count < limit) {
            window.count++;
            return true;
        }

        return false;
    }

    private static class Window{
        int count;
        long startTime;

        public Window(int count,long startTime){
            this.count = count;
            this.startTime = startTime;
        }
    }
}
