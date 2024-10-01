package es.upm.miw.apaw_practice.domain.models.bank;

import java.util.List;

public class BranchOffice {

    private String buildingName;
    private int employees;
    private int atmNumber;
    private List<Client> clients;

    public BranchOffice() {
        //Empty for framework
    }

    public BranchOffice(String buildingName, int employees, int atmNumber, List<Client> clients) {
        this.buildingName = buildingName;
        this.employees = employees;
        this.atmNumber = atmNumber;
        this.clients = clients;
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

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public int getAtmNumber() {
        return atmNumber;
    }

    public void setAtmNumber(int atmNumber) {
        this.atmNumber = atmNumber;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "BranchOffice{" +
                "BuildingName='" + buildingName + '\'' +
                ", employees='" + employees + '\'' +
                ", ATMs=" + atmNumber + '\'' +
                ", clients=" + clients.stream().map(Client::getDni).toList() +
                '}';
    }
}
