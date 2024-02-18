package com.info.local24.local24.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Trains")
public class Train {
    @Id
    @Column(name = "Train_Number", length = 5)
    private String trainNumber;
    @Column(name = "TR_NAME", nullable = false,unique = true)
    private String name;

    @ElementCollection
    @CollectionTable(name = "train_stations")
    @Column(name = "station")
    @JsonManagedReference
    private List<String> station;

}
