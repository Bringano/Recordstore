package se.yrgo.recordservice.domain.music.product;

import jakarta.persistence.*;
import se.yrgo.recordservice.domain.music.albums.Album;
import se.yrgo.recordservice.domain.music.Label;
import se.yrgo.recordservice.domain.Product;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MusicProduct extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @Enumerated(EnumType.STRING)
    private Packaging packaging;

    @Enumerated(EnumType.STRING)
    private PackagingProperty packagingProperty;
    private Date releaseDate;
    private String pressing;

    @ManyToMany
    private Set<Label> labels  = new HashSet<>();

    public MusicProduct() {
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    public PackagingProperty getPackagingProperty() {
        return packagingProperty;
    }

    public void setPackagingProperty(PackagingProperty packagingProperty) {
        this.packagingProperty = packagingProperty;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPressing() {
        return pressing;
    }

    public void setPressing(String pressing) {
        this.pressing = pressing;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }
}