package com.satrabench.getfriends.model;

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
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	private String name;

	private String password;
	private boolean supervisor = true;
	private boolean supervised = false;

	@OneToMany(mappedBy = "user",
			cascade = CascadeType.PERSIST,
			orphanRemoval = true)
	private List<Supervised> incidents = new ArrayList<>();

	@Lob
	@Column( length = 100000 )
	private String photo;

	@OneToMany(mappedBy = "user",
			cascade = CascadeType.PERSIST,
			orphanRemoval = true)
	private List<Invitation> invitations = new ArrayList<>();
}

