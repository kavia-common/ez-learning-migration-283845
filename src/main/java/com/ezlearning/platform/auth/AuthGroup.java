package com.ezlearning.platform.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "auth_user_group")
public class AuthGroup {
    @Id
    @Column(name = "auth_user_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "auth_group")
    private String authgroup;

    public AuthGroup(String username, String authgroup) {
        this.username = username;
        this.authgroup = authgroup;
    }
}
