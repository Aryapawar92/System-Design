import main.java.config.RateLimiterProperties;
import main.java.factory.RateLimiterFactory;
import main.java.service.RateLimiterService;


public class Main {
    public static void main(String[] args) {
        RateLimiterProperties props =
                new RateLimiterProperties(5, 1, "fixed_window");

        var limiter = RateLimiterFactory.create(props);
        var service = new RateLimiterService(limiter);

        for (int i = 1; i <= 100; i++) {
            boolean allowed = service.isAllowed("user1");
            System.out.println("Request " + i + " -> " + allowed);
        }
    }
}