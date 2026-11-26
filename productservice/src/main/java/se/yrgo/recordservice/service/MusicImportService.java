package se.yrgo.recordservice.service;

import jakarta.transaction.Transactional;
import se.yrgo.recordservice.domain.music.product.MusicProduct;

public class MusicImportService {

    @Transactional
    public MusicProduct importFromDiscogs(Long discogsReleaseId) {
        // 1. Hämta DiscogsReleaseDto från Discogs API

        // 2. Kontrollera om MusicProduct redan finns

        // 3. Hitta eller skapa Artist

        // 4. Hitta eller skapa Album

        // 5. Skapa MusicProduct och koppla till Album

        // 6. Lägg till bilder, format, label och genrer

        // 7. Spara och returnera MusicProduct
        return MusicProduct;
    }

}
