package es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities;

import es.upm.miw.apaw_practice.domain.models.zoo.Cage;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class CageEntity {

    @Id
    private String id;
    private Double size;
    @Indexed(unique = true)
    private String locationCode;
    private LocalDate nextFumigation;
    private Boolean cleaned;
    @DBRef
    private CaretakerEntity caretaker;
    @DBRef
    private ZooEntity zoo;
    @DBRef
    private List<AnimalEntity> animals;

    public CageEntity() {
        //empty from framework
    }

    public CageEntity(Cage cage, ZooEntity zoo, CaretakerEntity caretaker) {
        this.zoo = zoo;
        BeanUtils.copyProperties(cage, this);
        this.caretaker = caretaker;
        this.id = UUID.randomUUID().toString();
    }

    public Cage toCage() {
        return new Cage(this.size, this.locationCode, this.caretaker.toCaretaker());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public LocalDate getNextFumigation() {
        return nextFumigation;
    }

    public void setNextFumigation(LocalDate nextFumigation) {
        this.nextFumigation = nextFumigation;
    }

    public Boolean getCleaned() {
        return cleaned;
    }

    public void setCleaned(Boolean cleaned) {
        this.cleaned = cleaned;
    }

    public CaretakerEntity getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(CaretakerEntity caretaker) {
        this.caretaker = caretaker;
    }

    public ZooEntity getZoo() {
        return zoo;
    }

    public void setZoo(ZooEntity zoo) {
        this.zoo = zoo;
    }

    public List<AnimalEntity> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalEntity> animals) {
        this.animals = animals;
    }

    @Override
    public int hashCode() {
        return locationCode.hashCode() + zoo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || obj != null
                && getClass() == obj.getClass()
                && ((locationCode.equals(((CageEntity) obj).locationCode))
                && zoo.equals(((CageEntity) obj).getZoo()));
    }

    @Override
    public String toString() {
        return "Cage{" +
                "size=" + size +
                ", locationCode='" + locationCode + '\'' +
                ", nextFumigation=" + nextFumigation.toString() +
                ", cleaned=" + cleaned +
                caretaker.toString() +
                ", animals=" + animals.toString() +
                ", zoo=" + zoo.toString() +
                '}';
    }
}
