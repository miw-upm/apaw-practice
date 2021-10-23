package es.upm.miw.apaw_practice.domain.models.emarketer;

public class Customer {

    private String name;
    private String address;
    private String type;

    public Customer() {
        //empty for framework
    }

    public Customer(String name, String address, String type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name=" + name +
                ", address" + address +
                ", type=" + type +
                '}';
    }

    public static CustomerBuilders.Name builder() {
        return new Builder();
    }

    public static class Builder implements CustomerBuilders.Name, CustomerBuilders.Address, CustomerBuilders.Type,
            CustomerBuilders.Optionals {

        private Customer customer;

        public Builder() {
            this.customer = new Customer();
        }

        @Override
        public CustomerBuilders.Address name(String name) {
            this.customer.name = name;
            return this;
        }

        @Override
        public CustomerBuilders.Type address(String address) {
            this.customer.address = address;
            return this;
        }

        @Override
        public CustomerBuilders.Optionals type(String type) {
            this.customer.type = type;
            return this;
        }

        @Override
        public Customer build() {
            return this.customer;
        }
    }


}
