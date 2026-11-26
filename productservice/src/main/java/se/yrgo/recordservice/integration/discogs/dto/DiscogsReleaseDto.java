package se.yrgo.recordservice.integration.discogs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DiscogsReleaseDto(
        Long id,
        @JsonProperty("master_id") Long masterId,
        String title,
        Integer year,
        List<DiscogsArtistDto> artists,
        List<DiscogsFormatDto> formats,
        List<String> genres,
        List<String> styles,
        List<DiscogsImageDto> images
) {
}
