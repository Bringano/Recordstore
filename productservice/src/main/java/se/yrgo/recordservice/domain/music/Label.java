package se.yrgo.recordservice.domain.music;

import jakarta.persistence.*;

@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long discogsId;
    private String name;
}
