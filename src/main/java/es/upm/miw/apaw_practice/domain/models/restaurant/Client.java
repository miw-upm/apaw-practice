package es.upm.miw.apaw_practice.domain.models.restaurant;

import java.time.LocalDate;
import java.util.List;

public class Client {
    private String name;
    private String dni;
    private LocalDate registrationDate;
    private List<Waiter> waiters;
    private Table table;

    Client(){
        //empty for framework
    }

    public Client(String name, String dni, LocalDate registrationDate, List<Waiter> waiters, Table table) {
        this.name = name;
        this.dni = dni;
        this.registrationDate = registrationDate;
        this.waiters = waiters;
        this.table = table;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Waiter> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<Waiter> waiters) {
        this.waiters = waiters;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", registrationDate=" + registrationDate +
                ", waiters=" + waiters +
                ", table=" + table +
                '}';
    }
}
