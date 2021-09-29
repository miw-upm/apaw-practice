package es.upm.miw.apaw_practice.domain.models.zoo;

import java.util.ArrayList;

public class Zoo {

    private ZooAddress zooAddress;
    private Integer phoneNumber;
    private ArrayList<Cage> cages;

    public Zoo(ZooAddress zooAddress, Integer phoneNumber) {
        this.zooAddress = zooAddress;
        this.phoneNumber = phoneNumber;
        this.cages = new ArrayList<>();
    }

    public ZooAddress getZooAddress() {
        return zooAddress;
    }

    public void setZooAddress(ZooAddress zooAddress) {
        this.zooAddress = zooAddress;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Cage> getCages() {
        return cages;
    }

    public void setCages(ArrayList<Cage> cages) {
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
                zooAddress.toString() +
                ", phoneNumber=" + phoneNumber +
                ", cages=" + cages.toString() +
                '}';
    }
}
