package se.yrgo.recordservice.domain.music.albums;

import jakarta.persistence.*;
import se.yrgo.recordservice.domain.music.product.MusicProduct;
import se.yrgo.recordservice.domain.music.artists.Artist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private List<Artist> artists = new ArrayList<>();
    private Date yearRecorded;
    @OneToMany(mappedBy = "music_product")
    private MusicProduct musicProduct;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String info;

    public Album() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Date getYearRecorded() {
        return yearRecorded;
    }

    public void setYearRecorded(Date yearRecorded) {
        this.yearRecorded = yearRecorded;
    }

    public MusicProduct getPhysicalCopy() {
        return musicProduct;
    }

    public void setPhysicalCopy(MusicProduct musicProduct) {
        this.musicProduct = musicProduct;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
