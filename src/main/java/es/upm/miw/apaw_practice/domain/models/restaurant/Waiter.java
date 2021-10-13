package es.upm.miw.apaw_practice.domain.models.restaurant;

public class Waiter {
    private String section;
    private String category;

    public Waiter(){
        //empty for framework
    }

    public Waiter(String section, String category){
        this.section = section;
        this.category = category;
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


    @Override
    public String toString() {
        return "Waiter{" +
                "section='" + section + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
