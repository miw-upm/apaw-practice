package es.upm.miw.apaw_practice.domain.models.company;

import java.util.List;

public class Management {
    private int managerId;// Primary Key
    private String name;
    private boolean isActive;
    private List<Department> departments;

    public Management() {
        //empty for framework
    }
    public Management(int managerId, String name, boolean isActive) {
        this.managerId = managerId;
        this.name = name;
        this.isActive = isActive;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}

