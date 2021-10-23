package es.upm.miw.apaw_practice.domain.models.hospital;

import java.time.LocalDate;
import java.util.Objects;

public class Doctor {

    private String nick;
    private String surname;
    private LocalDate activeSince;

    public Doctor() {
        //empty for framework
    }

    public Doctor(String nick) {
        this.nick = nick;
        this.surname = null;
        this.activeSince = null;
    }

    public static DoctorBuilder.Nick builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(nick, doctor.nick) && Objects.equals(surname, doctor.surname) && Objects.equals(activeSince, doctor.activeSince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nick, surname, activeSince);
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(LocalDate activeSince) {
        this.activeSince = activeSince;
    }

    public static class Builder implements DoctorBuilder.Nick, DoctorBuilder.Surname,
            DoctorBuilder.ActiveSince, DoctorBuilder.Optionals {

        private final Doctor doctor;

        public Builder() {
            this.doctor = new Doctor();
        }

        @Override
        public DoctorBuilder.Surname nick(String nick) {
            this.doctor.nick = nick;
            return this;
        }

        @Override
        public DoctorBuilder.ActiveSince surname(String surname) {
            this.doctor.surname = surname;
            return this;
        }

        @Override
        public DoctorBuilder.Optionals activeSince(LocalDate activeSince) {
            this.doctor.activeSince = activeSince;
            return this;
        }

        @Override
        public Doctor build() {
            return this.doctor;
        }

    }
}
