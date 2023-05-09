package com.taina.backendjava.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="matkat")
public class Trip {
    @Id
    private int id;
    private Date departureDate;
    


}
