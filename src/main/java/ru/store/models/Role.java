package ru.store.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String enabled;

    public Role() {
    }

    public Role(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id)
                &&
                Objects.equals(enabled, role.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enabled);
    }

    @Override
    public String toString() {
        return "Role{"
                +
                "id="
                + id
                +
                ", enabled='"
                + enabled
                + '\''
                +
                '}';
    }
}
