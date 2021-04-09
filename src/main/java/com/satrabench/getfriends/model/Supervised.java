package com.satrabench.getfriends.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supervised {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;

    private String name;

    private String password;
    private boolean supervised = true;
    private boolean supervisor = false;

    @OneToMany(mappedBy = "supervised",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private List<Project> projects = new ArrayList<Project>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private List<BlackSite> blackSiteList = new ArrayList<>();

}
