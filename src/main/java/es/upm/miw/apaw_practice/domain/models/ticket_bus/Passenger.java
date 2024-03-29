package es.upm.miw.apaw_practice.domain.models.ticket_bus;

import java.util.List;
import java.util.Objects;

public class Passenger {
    private String nie;
    private String fullName;
    private Integer number;
    private Boolean accessibility;
    private List<Ticket> tickets;

    public Passenger() {
    }

    public Passenger(String nie, String fullName, Integer number, Boolean accessibility, List<Ticket> tickets) {
        this.nie = nie;
        this.fullName = fullName;
        this.number = number;
        this.accessibility = accessibility;
        this.tickets = tickets;
    }

    public String getNie() {
        return nie;
    }

    public void setNie(String nie) {
        this.nie = nie;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Boolean accessibility) {
        this.accessibility = accessibility;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger passenger)) return false;
        return Objects.equals(nie, passenger.nie)
                && Objects.equals(fullName, passenger.fullName)
                && Objects.equals(number, passenger.number)
                && Objects.equals(accessibility, passenger.accessibility)
                && Objects.equals(tickets, passenger.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nie, fullName, number, accessibility);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "nie='" + nie + '\'' +
                ", fullName='" + fullName + '\'' +
                ", number=" + number +
                ", accessibility=" + accessibility +
                ", tickets=" + tickets +
                '}';
    }
}
