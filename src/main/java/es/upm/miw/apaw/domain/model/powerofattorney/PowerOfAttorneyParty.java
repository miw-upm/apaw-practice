package es.upm.miw.apaw.domain.model.powerofattorney;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import java.util.UUID;

public class PowerOfAttorneyParty {

    private final UUID id;
    private Integer age;
    private Boolean fullMentalCapacity;
    private String companyName;
    private Boolean representationCompany;
    private UserSnapshot userSnapshot;

    public PowerOfAttorneyParty(Integer age, UserSnapshot userSnapshot) {
        this.id = UUID.randomUUID();
        this.age = age;
        this.fullMentalCapacity = true;
        this.companyName = null;
        this.representationCompany = false;
        this.userSnapshot = userSnapshot;
    }

    public UUID getId() {
        return this.id;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getFullMentalCapacity() {
        return this.fullMentalCapacity;
    }

    public void setFullMentalCapacity(Boolean fullMentalCapacity) {
        this.fullMentalCapacity = fullMentalCapacity;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getRepresentationCompany() {
        return this.representationCompany;
    }

    public void setRepresentationCompany(Boolean representationCompany) {
        this.representationCompany = representationCompany;
    }

    public UserSnapshot getUserSnapshot() {
        return this.userSnapshot;
    }

    public void setUserSnapshot(UserSnapshot userSnapshot) {
        this.userSnapshot = userSnapshot;
    }
}
