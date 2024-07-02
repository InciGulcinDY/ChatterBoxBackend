package com.tobeto.ChatterBoxBackend.entities.abstracts;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@MappedSuperclass
@Data
public abstract class BasicEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_date")
    private LocalDate createdDate;



    @PrePersist
    private void beforeAdd() {
        createdDate = LocalDate.now();
    }


}
