package se.yrgo.recordservice.integration.discogs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DiscogsArtistDto(
        Long id,
        String name
) {
}
