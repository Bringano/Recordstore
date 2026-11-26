package se.yrgo.recordservice.integration.discogs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DiscogsImageDto(
        String type,
        String url
) {
}
