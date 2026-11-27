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
            @Value("${discogs.token}") String token,
            @Value("${discogs.base-url}") String baseUrl,
            @Value("${discogs.user-agent}") String userAgent) {

        if (token.isBlank()) {
            throw new IllegalStateException(
              "Missing Discogs token"
            );
        }

        this.restClient = builder
                .baseUrl(baseUrl)
                .defaultHeader(
                        HttpHeaders.USER_AGENT,
                        userAgent
                )
                .defaultHeader(
                        HttpHeaders.AUTHORIZATION,
                        "Discogs token=" + token
                )
                .build();
    }

    public DiscogsSearchResponse searchRelease(String query) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/database/search")
                        .queryParam("q", "{query}")
                        .queryParam("type", "release")
                        .build(query))
                .retrieve()
                .body(DiscogsSearchResponse.class);
    }

    public DiscogsReleaseDto getRelease(Long releaseId) {

        return restClient.get()
                .uri("/releases/{id}", releaseId)
                .retrieve()
                .body(DiscogsReleaseDto.class);
    }

    //getArtist()
    //getLabel()
    //getMasterRelease()
}
