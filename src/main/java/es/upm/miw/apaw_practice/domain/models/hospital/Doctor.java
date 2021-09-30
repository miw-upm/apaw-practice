package es.upm.miw.apaw_practice.domain.models.hospital;

import java.time.LocalDate;

public class Doctor {

    private String nick;
    private String surname;
    private LocalDate activeSince;

    Doctor(){
        //empty for framework
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

    @Override
    public String toString() {
        return "Doctor{" +
                "nick='" + this.nick + '\'' +
                ", surname='" + this.surname + '\'' +
                ", activeSince=" + this.activeSince +
                '}';
    }
}