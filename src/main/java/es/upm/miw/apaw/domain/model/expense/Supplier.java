package es.upm.miw.apaw.domain.model.expense;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Supplier {
    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String taxId;

    @NotBlank
    private String companyName;

    private String address;
    private String contactEmail;
    private String corporatePhone;
    private Integer paymentTermsDays;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.paymentTermsDays == null) {
            this.paymentTermsDays = 30;
        }
    }
}