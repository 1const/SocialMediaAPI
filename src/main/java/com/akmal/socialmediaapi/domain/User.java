package com.akmal.socialmediaapi.domain;


import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "uuser")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String mail;

    @Column
    private String password;

    @Column
    private String role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "subscriber", orphanRemoval = true)
    private Set<Subscription> subscriptions = new HashSet<>();

    @OneToMany(
            mappedBy = "publisher",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Subscription> subscribers = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
