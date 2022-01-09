package es.upm.miw.apaw_practice.domain.models.training;

public class ParticipantEmailUpdating {
    private String dni;
    private String email;

    public ParticipantEmailUpdating() {
        //empty from framework
    }

    public ParticipantEmailUpdating(String dni, String email) {
        this.dni = dni;
        this.email = email;
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

    @Override
    public String toString() {
        return "ParticipantEmailUpdating{" +
                "dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
