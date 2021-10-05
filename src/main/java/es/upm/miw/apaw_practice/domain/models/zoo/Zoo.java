package es.upm.miw.apaw_practice.domain.models.zoo;

import java.util.ArrayList;
import java.util.List;

public class Zoo {

    private ZooAddress address;
    private Integer phoneNumber;
    private List<Cage> cages;

    public Zoo() {
        //emtpy from framework
    }

    public Zoo(ZooAddress address, Integer phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cages = new ArrayList<>();
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
                address.toString() +
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
