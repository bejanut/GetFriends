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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;

    @OneToMany(mappedBy = "project",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private List<Task> projects = new ArrayList<Task>();

    private String name;
    private String description;
    private int completion;
    private boolean isDone;
    private int completedTasks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supervised")
    @JsonIgnore
    private Supervised supervised;

    public boolean isDone(Project project) {
        return project.completion == 100;
    }
}
