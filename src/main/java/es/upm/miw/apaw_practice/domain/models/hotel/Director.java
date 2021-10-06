package es.upm.miw.apaw_practice.domain.models.hotel;

import es.upm.miw.apaw_practice.domain.models.shop.Article;

public class Director {

    private String dniDirector;
    private String email;
    private Integer telephone;

    public Director() {
        //empty for framework
    }

    public Director(String dniDirector, String email, Integer telephone) {
        this.dniDirector = dniDirector;
        this.email = email;
        this.telephone = telephone;
    }

    public static Director ofEmail(Director director) {
        Director directorDto = new Director();
        directorDto.setEmail(director.getEmail());
        return directorDto;
    }

    public String getDniDirector() {
        return dniDirector;
    }

    public void setDniDirector(String dniDirector) {
        this.dniDirector = dniDirector;
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

    @Override
    public String toString() {
        return "Director{" +
                "dniDirector='" + dniDirector + '\'' +
                ", email='" + email + '\'' +
                ", telephone=" + telephone +
                '}';
    }
}
