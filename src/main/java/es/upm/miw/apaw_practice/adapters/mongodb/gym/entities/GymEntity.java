package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities;


import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class GymEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String address;
    private String label;
    private String cellphone;
    @DBRef
    private List<CoachEntity> coach;

    public GymEntity() {
        //empty
    }

    public GymEntity(String address, String label, String cellphone, List<CoachEntity> coach) {
        this.id = UUID.randomUUID().toString();
        this.address = address;
        this.label = label;
        this.cellphone = cellphone;
        this.coach = coach;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public List<CoachEntity> getCoach() {
        return coach;
    }

    public void setCoach(List<CoachEntity> coach) {
        this.coach = coach;
    }

    public Gym ToGym() {
        Gym gym = new Gym();
        BeanUtils.copyProperties(this, gym);
        return gym;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GymEntity gymEntity = (GymEntity) o;
        return address.equals(gymEntity.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "GymEntity{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", label='" + label + '\'' +
                ", cellphone=" + cellphone +
                ", coach=" + coach +
                '}';
    }
}
