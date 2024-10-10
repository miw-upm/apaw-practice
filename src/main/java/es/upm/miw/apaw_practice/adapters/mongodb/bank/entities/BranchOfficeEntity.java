package es.upm.miw.apaw_practice.adapters.mongodb.bank.entities;

import es.upm.miw.apaw_practice.domain.models.bank.BranchOffice;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class BranchOfficeEntity {

    @Id
    private String id;
    private String buildingName;
    private Integer employees;
    private Integer atmNumber;
    @DBRef
    private List<ClientEntity> clientsEntities;

    public BranchOfficeEntity() {
        //Empty for framework
    }

    public BranchOfficeEntity(String buildingName, Integer employees, Integer atmNumber, List<ClientEntity> clientsEntities) {
        this.id = UUID.randomUUID().toString();
        this.buildingName = buildingName;
        this.employees = employees;
        this.atmNumber = atmNumber;
        this.clientsEntities = clientsEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }

    public int getAtmNumber() {
        return atmNumber;
    }

    public void setAtmNumber(Integer atmNumber) {
        this.atmNumber = atmNumber;
    }

    public List<ClientEntity> getClients() {
        return clientsEntities;
    }

    public void setClients(List<ClientEntity> clientsEntities) {
        this.clientsEntities = clientsEntities;
    }

    public BranchOffice toBranchOffice() {
        List<Client> clients = this.clientsEntities.stream()
                .map(ClientEntity::toClient)
                .toList();
        return new BranchOffice(buildingName, employees, atmNumber, clients);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((BranchOfficeEntity) obj).id));
    }

    @Override
    public String toString() {
        return "BranchOffice{" +
                "BuildingName='" + buildingName + '\'' +
                ", employees='" + employees + '\'' +
                ", ATMs=" + atmNumber + '\'' +
                ", clients=" + clientsEntities.stream().map(ClientEntity::getDni).toList() +
                '}';
    }

}
