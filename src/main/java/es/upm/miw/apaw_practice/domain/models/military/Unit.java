package es.upm.miw.apaw_practice.domain.models.military;

import java.util.List;

public class Unit {
    private String name;
    private String branch;
    private String location;
    private List<Soldier> soldiers;

    public Unit() {
        //empty for framework
    }

    public Unit(String name, String branch, String location, List<Soldier> soldiers) {
        this.name = name;
        this.branch = branch;
        this.location = location;
        this.soldiers = soldiers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Soldier> getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(List<Soldier> soldiers) {
        this.soldiers = soldiers;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (name.equals(((Unit) obj).name));
    }

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                ", location='" + location + '\'' +
                ", soldiers=" + soldiers.stream().map(Soldier::getIdentityDocument).toList() +
                '}';
    }
}
