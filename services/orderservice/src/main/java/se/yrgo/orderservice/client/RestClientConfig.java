package se.yrgo.orderservice.client;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(ProductServiceProperties.class)
public class RestClientConfig {
    @Bean
    RestClient productRestClient(ProductServiceProperties properties) {
        return RestClient.builder().baseUrl(properties.getBaseUrl()).build();
    }
}
