package se.yrgo.recordservice.domain.music.artists;

import jakarta.persistence.*;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;
    private String bio;
}
