package se.yrgo.recordservice.integration.discogs.dto;

import java.util.List;

public record DiscogsSearchResultDto(
        Long id,
        String title,
        Integer year,
        String country,
        String thumb,
        List<String> format
) {
}
