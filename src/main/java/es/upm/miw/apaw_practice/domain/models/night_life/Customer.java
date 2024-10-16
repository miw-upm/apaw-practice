package es.upm.miw.apaw_practice.domain.models.night_life;

public class Customer {
    private String name;
    private String phone;
    private String email;

    public Customer(){
        //empty for framework
    }

    public Customer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public static CustomerBuilders.Name builder() {
        return new Builder();
    }
    private static class Builder implements CustomerBuilders.Name, CustomerBuilders.Phone, CustomerBuilders.Optionals {

        private final Customer customer;

        public Builder() {
            this.customer = new Customer();
        }

        @Override
        public CustomerBuilders.Phone name(String name) {
            this.customer.name = name;
            return this;
        }

        @Override
        public CustomerBuilders.Optionals phone(String phone) {
            this.customer.phone = phone;
            return this;
        }

        @Override
        public CustomerBuilders.Optionals email(String email) {
            this.customer.email = email;
            return this;
        }

        @Override
        public Customer build() {
            return this.customer;
        }
    }

}
