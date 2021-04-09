package com.satrabench.getfriends.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;

    private String status = "pending";
    private String name;
    private int idSuper;
    private int idUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private User user;

    public Invitation(int idSuper, int idUser) {
        this.idSuper = idSuper;
        this.idUser = idUser;
    }
}
