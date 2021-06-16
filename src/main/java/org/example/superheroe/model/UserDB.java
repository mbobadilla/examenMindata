package org.example.superheroe.model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class UserDB  {
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="rol")
    private String rol;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
