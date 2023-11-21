package main.java.com.alumni.insight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InsightApplication {
    public static void main(String[] args) {
            SpringApplication.run(InsightApplication.class, args);
        }
}
