package es.upm.miw.apaw_practice.domain.models.hotel;

public class Director {

    private String dniDirector;
    private String email;
    private int telephone;

    Director() {
        //empty for framework
    }

    public Director(String dniDirector, String email, int telephone) {
        this.dniDirector = dniDirector;
        this.email = email;
        this.telephone = telephone;
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
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
