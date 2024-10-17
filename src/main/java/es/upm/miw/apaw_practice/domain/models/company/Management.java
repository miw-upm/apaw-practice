package es.upm.miw.apaw_practice.domain.models.company;

import java.util.List;

public class Management {
    private String managerId;// Primary Key
    private String name;
    private boolean Activated;
    

    public Management() {
        //empty for framework
    }
    public Management(String managerId, String name, boolean Activated) {
        this.managerId = managerId;
        this.name = name;
        this.Activated = Activated;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActivated() {
        return Activated;
    }

    public void setActivated(boolean activated) {
        Activated = activated;
    }


}

