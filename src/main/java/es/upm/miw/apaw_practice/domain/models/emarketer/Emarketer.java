package es.upm.miw.apaw_practice.domain.models.emarketer;

import java.util.List;

public class Emarketer {

    private String name;
    private String address;
    private Boolean systemic;
    private List<Cups> cups;
    private List<Plan> plans;

    public Emarketer() {
        //empty for framework
    }

    public Emarketer(String name, String address, Boolean systemic, List<Cups> cups, List<Plan> plans) {
        this.name = name;
        this.address = address;
        this.systemic = systemic;
        this.cups = cups;
        this.plans = plans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdrress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isSystemic() {
        return systemic;
    }

    public void setSystemic(Boolean systemic) {
        this.systemic = systemic;
    }

    public List<Cups> getCups() {
        return cups;
    }

    public void setCups(List<Cups> cups) {
        this.cups = cups;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlan(List<Plan> plans) {
        this.plans = plans;
    }

    @Override
    public String toString() {
        return "Emarketer{" +
                "name=" + name +
                ", address=" + address +
                ", systemic=" + systemic +
                '}';
    }

}
