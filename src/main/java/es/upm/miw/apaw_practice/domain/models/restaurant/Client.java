package es.upm.miw.apaw_practice.domain.models.restaurant;

import java.time.LocalDate;
import java.util.List;

public class Client {
    private String dni;
    private String name;
    private LocalDate registrationDate;
    private List<Waiter> waiters;
    private Table table;

    public Client(){
        //empty for framework
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
