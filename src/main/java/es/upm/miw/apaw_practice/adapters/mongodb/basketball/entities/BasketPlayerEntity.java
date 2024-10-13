package es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketPlayer;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class BasketPlayerEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String dni;
    private String name;
    private int dorsal;
    private int points;

    public BasketPlayerEntity() {
        // empty for framework
    }

    public BasketPlayerEntity(String dni, String name, int dorsal, int points) {
        this.id = dni;  // Use the dni as the unique identifier.
        this.dni = dni;
        this.name = name;
        this.dorsal = dorsal;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public BasketPlayer toBasketPlayer() {
        BasketPlayer basketPlayer = new BasketPlayer();
        BeanUtils.copyProperties(this, basketPlayer);
        return basketPlayer;
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && dni.equals(((BasketPlayerEntity) obj).dni));
    }

    @Override
    public String toString() {
        return "BasketPlayerEntity{" +
                "id='" + id + '\'' +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", dorsal=" + dorsal +
                ", points=" + points +
                '}';
    }
}