package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities;

import es.upm.miw.apaw_practice.domain.models.restaurant.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class ClientEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String dni;
    private String name;
    private LocalDate registrationDate;
    @DBRef
    private List<WaiterEntity> waiters;
    @DBRef
    private TableEntity table;

    public ClientEntity(){
        //empty for framework
    }

    public ClientEntity(String dni, String name, LocalDate  registrationDate, List<WaiterEntity> waiters, TableEntity table) {
        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.name = name;
        this.registrationDate = registrationDate;
        this.waiters = waiters;
        this.table = table;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<WaiterEntity> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<WaiterEntity> waiters) {
        this.waiters = waiters;
    }

    public TableEntity getTable() {
        return table;
    }

    public void setTable(TableEntity table) {
        this.table = table;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ClientEntity) obj).id));
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", registrationDate=" + registrationDate +
                ", waiters=" + waiters +
                ", table=" + table +
                '}';
    }

    public Client toClient() {
        Client client = new Client();
        BeanUtils.copyProperties(this, client);
        return client;
    }
}
