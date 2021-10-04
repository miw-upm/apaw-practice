package es.upm.miw.apaw_practice.domain.models.car_hire;

public class Renter {

    private String name;
    private String DNI;
    private Boolean likedCar;

    public Renter() {
        //empty for framework
    }

    public Renter(String name, String DNI, Boolean likedCar) {
        this.name = name;
        this.DNI = DNI;
        this.likedCar = likedCar;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDNI() {
        return this.DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Boolean getLikedCar() {
        return this.likedCar;
    }

    public void setLikedCar(Boolean likedCar) {
        this.likedCar = likedCar;
    }

    @Override
    public String toString() {
        return "Renter{" +
                "name='" + name + '\'' +
                ", DNI='" + DNI + '\'' +
                ", likedCar=" + likedCar +
                '}';
    }
}
