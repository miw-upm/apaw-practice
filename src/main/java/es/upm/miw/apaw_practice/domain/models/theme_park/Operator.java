package es.upm.miw.apaw_practice.domain.models.theme_park;

public class Operator {
    private Ride ride;
    private String name;
    private String address;

    public Operator() {
        //empty from framework
    }

    public Operator(Ride ride, String name, String address) {
        this.ride = ride;
        this.name = name;
        this.address = address;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
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

    @Override
    public String toString() {
        return "Operator{" +
                "Ride=" + ride +
                ", name=" + name +
                ", address=" + address +
                '}';
    }
}
