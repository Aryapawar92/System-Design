package config;

public class RateLimiterProperties {
    private final int limit;
    private final long windowSizeMs;
    private final String algorithms;

    public RateLimiterProperties(int limit,long windowSizeMs,String algorithms){
        this.limit = limit;
        this.windowSizeMs = windowSizeMs;
        this.algorithms = algorithms;
    }

    public int getLimit(){return limit;}
    public long getWindowSizeMs(){return windowSizeMs;}
    public String getAlgorithms(){return algorithms;}
}