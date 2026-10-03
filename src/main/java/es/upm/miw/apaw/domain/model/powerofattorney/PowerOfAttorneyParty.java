package es.upm.miw.apaw.domain.model.powerofattorney;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PowerOfAttorneyParty {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private Integer age;

    @NotNull
    private Boolean fullMentalCapacity;

    private String companyName;

    @NotNull
    private Boolean representationCompany;

    @NotNull
    private UserSnapshot userSnapshot;

    public void doDefault() {
        this.id = UUID.randomUUID();

        if (this.fullMentalCapacity == null) {
            this.fullMentalCapacity = true;
        }

        if (this.representationCompany == null) {
            this.representationCompany = false;
        }
    }
}
