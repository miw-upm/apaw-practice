package es.upm.miw.apaw_practice.domain.models.car;

public interface ManufacturerBuilders {

    interface Name{
        Country name(String name);
    }

    interface Country{
        NumberOfEmployees country(String country);
    }
    interface NumberOfEmployees{
        Builder numberOfEmployees(Integer numberOfEmployees);
    }

    interface Builder{
        Manufacturer build();
    }
}
