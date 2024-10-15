package es.upm.miw.apaw_practice.domain.models.bank;

import java.util.List;

public interface ClientBuilders {

    interface Dni {
        Name dni(String dni);
    }

    interface Name {
        Surname name(String name);
    }

    interface Surname {
        PhoneNumber surname(String surname);
    }

    interface PhoneNumber {
        Email phoneNumber(Integer phoneNumber);
    }

    interface Email {
        Optionals email(String email);
    }

    interface Optionals {
        Optionals investmentFunds(List<InvestmentFund> investmentFunds);
        Client build();
    }
}
