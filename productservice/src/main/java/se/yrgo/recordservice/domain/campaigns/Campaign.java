package se.yrgo.recordservice.domain.campaigns;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
}
