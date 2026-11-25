package se.yrgo.recordservice.domain.music.product;

import java.util.Set;

public enum Packaging {
    // Vinyl
    STANDARD_SLEEVE(Media.VINYL),
    GATEFOLD(Media.VINYL),
    TRIFOLD(Media.VINYL),
    PICTURE_SLEEVE(Media.VINYL),
    GENERIC_SLEEVE(Media.VINYL),
    DIE_CUT_SLEEVE(Media.VINYL),

    // CD
    JEWEL_CASE(Media.CD),
    SLIM_JEWEL_CASE(Media.CD),
    DOUBLE_JEWEL_CASE(Media.CD),
    FATBOX(Media.CD),
    SUPER_JEWEL_BOX(Media.CD),
    DIGIPAK(Media.CD),
    DIGISLEEVE(Media.CD),
    DIGIFILE(Media.CD),
    ECO_WALLET(Media.CD),
    LONGBOX(Media.CD),

    // Kassett
    CASSETTE_CASE(Media.CASSETTE),
    CASSETTE_SNAP_CASE(Media.CASSETTE),
    CASSETTE_SOFT_CASE(Media.CASSETTE),

    // Gemensamma för flera format
    CARDBOARD_SLEEVE(Media.VINYL, Media.CD, Media.CASSETTE),
    PAPER_SLEEVE(Media.VINYL, Media.CD, Media.CASSETTE),
    SLIPCASE(Media.VINYL, Media.CD, Media.CASSETTE),
    BOX_SET(Media.VINYL, Media.CD, Media.CASSETTE),
    METAL_TIN(Media.VINYL, Media.CD, Media.CASSETTE),
    HARDCOVER_BOOK(Media.VINYL, Media.CD, Media.CASSETTE),

    // Övrigt
    NO_PACKAGING(Media.VINYL, Media.CD, Media.CASSETTE),
    OTHER(Media.VINYL, Media.CD, Media.CASSETTE);

    private final Set<Media> allowedFormats;

    Packaging(Media... formats) {
        this.allowedFormats = Set.of(formats);
    }

    public boolean isValidFor(Media format) {
        return allowedFormats.contains(format);
    }
}
