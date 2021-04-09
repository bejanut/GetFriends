package com.satrabench.getfriends.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    private String name;

    private String description;

    private boolean isDone;

    @Lob
    @Column( length = 100000 )
    private String photo;


    private Date deadline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;

}
