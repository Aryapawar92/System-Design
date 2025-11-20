package main.java.factory;

import main.java.algorithms.LeakyBucketAlgo;
import main.java.algorithms.SlidingWindowAlgo;
import main.java.algorithms.TokenBucketAlgo;
import main.java.algorithms.fixedWindowAlgo;
import main.java.config.RateLimiterProperties;
import main.java.interfaces.RateLimiter;

public class RateLimiterFactory {
    public static RateLimiter create(RateLimiterProperties props){

        AlgorithmType type = AlgorithmType.valueOf(
                props.getAlgorithms().toUpperCase()
        );

        return switch (type){
            case FIXED_WINDOW -> new fixedWindowAlgo(props.getLimit(), props.getWindowSizeMs());
            case SLIDING_WINDOW -> new SlidingWindowAlgo(props.getLimit(), props.getWindowSizeMs());
            case TOKEN_BUCKET -> new TokenBucketAlgo(props.getLimit(),props.getLimit() / 1.0);
            case LEAKY_BUCKET -> new LeakyBucketAlgo(props.getLimit(), props.getLimit() / 1.0);
        };
    }
}
