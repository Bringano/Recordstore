package se.yrgo.recordservice.integration.discogs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import se.yrgo.recordservice.integration.discogs.dto.DiscogsSearchResultDto;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DiscogsSearchResponse(
        List<DiscogsSearchResultDto> results
) {
}
