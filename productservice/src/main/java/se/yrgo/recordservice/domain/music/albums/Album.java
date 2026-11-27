package se.yrgo.recordservice.domain.music.albums;

import jakarta.persistence.*;
import se.yrgo.recordservice.domain.music.product.MusicProduct;
import se.yrgo.recordservice.domain.music.artists.Artist;

import java.util.*;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private Set<Artist> artists = new HashSet<>();
    private Date yearRecorded;
    @OneToMany(mappedBy = "album")
    private Set<MusicProduct> musicProducts = new HashSet<>();

    @ManyToMany
    private Set<Genre> genres = new HashSet<>();
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

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Date getYearRecorded() {
        return yearRecorded;
    }

    public void setYearRecorded(Date yearRecorded) {
        this.yearRecorded = yearRecorded;
    }

    public Set<MusicProduct> getMusicProducts() {
        return musicProducts;
    }

    public void setMusicProducts(Set<MusicProduct> musicProducts) {
        this.musicProducts = musicProducts;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
