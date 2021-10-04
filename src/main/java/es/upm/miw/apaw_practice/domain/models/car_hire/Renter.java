package es.upm.miw.apaw_practice.domain.models.car_hire;

public class Renter {

    private String name;
    private String dni;
    private Boolean likedCar;

    public Renter() {
        //empty for framework
    }

    public Renter(String name, String dni, Boolean likedCar) {
        this.name = name;
        this.dni = dni;
        this.likedCar = likedCar;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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
                ", DNI='" + dni + '\'' +
                ", likedCar=" + likedCar +
                '}';
    }
}
