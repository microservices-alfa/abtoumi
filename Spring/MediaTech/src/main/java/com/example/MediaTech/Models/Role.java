package com.example.MediaTech.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private Integer roleId;
    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name="user_roles",joinColumns = @JoinColumn(name = "roleId"),inverseJoinColumns = @JoinColumn(name ="userId" ))
    private List<User> users;

}