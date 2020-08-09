package ru.store.models;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ADVERT")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private String price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @Column(name = "advert_status")
    private boolean status = true;

    @Column(name = "added")
    private Timestamp added;

    public Advert() {
    }

    public Advert(String title, String price, Car car) {
        this.title = title;
        this.price = price;
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advert advert = (Advert) o;
        return status == advert.status &&
                Objects.equals(id, advert.id) &&
                Objects.equals(title, advert.title) &&
                Objects.equals(price, advert.price) &&
                Objects.equals(user, advert.user) &&
                Objects.equals(car, advert.car) &&
                Objects.equals(photo, advert.photo) &&
                Objects.equals(added, advert.added);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, user, car, status);
    }


    @Override
    public String toString() {
        return "Advert{"
                +
                "id="
                + id
                +
                ", title='"
                + title
                + '\''
                +
                ", price='"
                + price
                + '\''
                +
                ", user="
                + user
                +
                ", car="
                + car
                +
                ", status="
                + status
                +
                '}';
    }

}
