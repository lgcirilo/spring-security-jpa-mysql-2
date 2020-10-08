package com.lgcirilo.springsecurityjpamysql2.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String role;

    @ManyToMany(mappedBy = "roles",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<User> users;


}
