package se.yrgo.orderservice.client;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import se.yrgo.orderservice.client.exceptions.ProductNotFoundException;

@Component
public class RestProductClient implements ProductClient {
    private final RestClient restClient;

    public RestProductClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @SuppressWarnings("null")
    @Override
    public ProductInfo getProduct(Long productId) {
        return restClient.get()
                .uri("/api/products/{id}", productId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new ProductNotFoundException("Product " + productId + " not found");
                })
                .body(ProductInfo.class);
    }
}
