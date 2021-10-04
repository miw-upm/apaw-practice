package es.upm.miw.apaw_practice.domain.models.restaurant;

import java.util.List;

public class Waiter {
    private String section;
    private String category;
    private List<Client> clients;

    Waiter(){
        //empty for framework
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Waiter{" +
                "section='" + section + '\'' +
                ", category='" + category + '\'' +
                ", clients=" + clients +
                '}';
    }
}
