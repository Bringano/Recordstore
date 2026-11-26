package se.yrgo.recordservice.integration.discogs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DiscogsFormatDto(
        String name,
        List<String> descriptions
) {
}
