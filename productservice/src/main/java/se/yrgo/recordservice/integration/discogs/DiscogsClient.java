package se.yrgo.recordservice.integration.discogs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import se.yrgo.recordservice.integration.discogs.dto.DiscogsReleaseDto;

@Component
public class DiscogsClient {

    private final RestClient restClient;

    public DiscogsClient(
            RestClient.Builder builder,
            @Value("${discogs.token}") String token) {
        this.restClient = builder
                .baseUrl("https://api.discogs.com")
                .defaultHeader(
                        HttpHeaders.USER_AGENT,
                        "RecordService/1.0"
                )
                .defaultHeader(
                        HttpHeaders.AUTHORIZATION,
                        "Discogs token=" + token
                )
                .build();
    }

    public DiscogsSearchResponse searchRelease(String query) {
        return restClient.get()
    }

    public DiscogsReleaseDto getRelease(Long releaseId) {

        return restClient.get()
                .uri("/releases/{id}", releaseId)
                .retrieve()
                .body(DiscogsReleaseDto.class);
    }
}
