package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.List;

public class Director {

    private String dni;
    private String email;
    private Integer telephone;
    private List<Hotel> hotelList;

    public Director() {
        //empty for framework
    }

    public Director(String dni, String email, Integer telephone, List<Hotel> hotelList) {
        this.dni = dni;
        this.email = email;
        this.telephone = telephone;
        this.hotelList = hotelList;
    }

    public static Director ofEmail(Director director) {
        Director directorDto = new Director();
        directorDto.setEmail(director.getEmail());
        return directorDto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    @Override
    public String toString() {
        return "Director{" +
                "dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", telephone=" + telephone +
                ", hotelList=" + hotelList +
                '}';
    }

}
