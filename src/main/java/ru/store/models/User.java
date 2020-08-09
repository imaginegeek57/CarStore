package ru.store.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login", unique = true, nullable = false)
    @Size(min = 5)
    private String login;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY)
    List <Advert> advertList = new ArrayList <>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

    public User() {
    }

    public User(String login, String password, List <Advert> advertList) {
        this.login = login;
        this.password = password;
        this.advertList = advertList;
    }

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(advertList, user.advertList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, advertList);
    }

    @Override
    public String toString() {
        return "User{"
                +
                "id="
                + id
                +
                ", login='"
                + login
                + '\''
                +
                ", password='"
                + password
                + '\''
                +
                ", advertList="
                + advertList
                +
                '}';
    }
}
