package es.upm.miw.apaw_practice.domain.models.zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Zoo {

    private String id;
    private ZooAddress address;
    private Integer phoneNumber;
    private List<Cage> cages;

    public Zoo() {
        //emtpy from framework
    }

    public Zoo(ZooAddress address, Integer phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cages = new ArrayList<>();
    }

    public Zoo(String id, ZooAddress address, Integer phoneNumber) {
        this(address, phoneNumber);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZooAddress getAddress() {
        return address;
    }

    public void setAddress(ZooAddress address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Cage> getCages() {
        return cages;
    }

    public void setCages(List<Cage> cages) {
        this.cages = cages;
    }

    public void addCage(Cage cage) {
        this.cages.add(cage);
    }

    public boolean removeCage(Cage cage) {
        return this.cages.remove(cage);
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "id=" + phoneNumber +
                ", " + address.toString() +
                ", phoneNumber=" + phoneNumber +
                ", cages=" + cages.toString() +
                '}';
    }

    public boolean isNull() {
        return this.phoneNumber == null
                || this.address == null
                || this.address.isNull();
    }
}
