package es.upm.miw.apaw_practice.domain.models.hospital;

public class Hospital {

    private String name;
    private String address;
    private Integer availableRooms;

    Hospital(){
        //empty for framework
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(Integer availableRooms) {
        this.availableRooms = availableRooms;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name='" + this.name + '\'' +
                ", address='" + this.address + '\'' +
                ", availableRooms=" + this.availableRooms +
                '}';
    }
}
