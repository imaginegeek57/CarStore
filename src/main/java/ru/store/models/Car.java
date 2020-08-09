package ru.store.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "CAR")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "car_model", nullable = false)
    private String title;

    @Column(name = "car_vincode")
    private String vincode;

    @Column(name = "car_body")
    @Enumerated(EnumType.STRING)
    private CarBody carBody;

    public Car() {
    }

    public Car(String title, String vincode, CarBody carBody) {
        this.title = title;
        this.carBody = carBody;
        this.vincode = vincode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) &&
                Objects.equals(title, car.title) &&
                Objects.equals(vincode, car.vincode) &&
                carBody == car.carBody;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, vincode, carBody);
    }

    @Override
    public String toString() {
        return "Car{"
                +
                "id="
                + id
                +
                ", title='"
                + title
                + '\''
                +
                ", vincode='"
                + vincode
                + '\''
                +
                ", carBody="
                + carBody
                +
                '}';
    }

}
