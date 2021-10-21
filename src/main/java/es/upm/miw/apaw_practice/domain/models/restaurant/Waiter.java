package es.upm.miw.apaw_practice.domain.models.restaurant;

import java.util.UUID;

public class Waiter {
    private String id;
    private String section;
    private String category;

    public Waiter(){
        //empty for framework
    }

    public static WaiterBuilder.Id builder(){
        return new Builder();
    }

    public Waiter(String section, String category){
        this.id = UUID.randomUUID().toString();
        this.section = section;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public static Waiter ofSection(Waiter waiter) {
        Waiter waiterDto = new Waiter();
        waiterDto.setSection(waiter.getSection());
        return waiterDto;
    }

    public static class Builder implements WaiterBuilder.Id, WaiterBuilder.Optionals{

        private final Waiter waiter;

        private Builder() {
            this.waiter = new Waiter();
        }

        @Override
        public WaiterBuilder.Optionals id(String id) {
            this.waiter.id = id;
            return this;
        }

        @Override
        public WaiterBuilder.Optionals section(String section) {
            this.waiter.section = section;
            return this;
        }

        @Override
        public WaiterBuilder.Optionals category(String category) {
            this.waiter.category = category;
            return this;
        }

        @Override
        public Waiter build() {
            return this.waiter;
        }
    }

}
