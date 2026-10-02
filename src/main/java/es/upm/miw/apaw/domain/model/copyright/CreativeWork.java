package es.upm.miw.apaw.domain.model.copyright;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreativeWork {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String registrationCode;

    @NotBlank
    private String title;

    @NotNull
    private BigDecimal estimatedValuation;

    private LocalDate registrationDate;

    @NotBlank
    private String authorPenName;

    private FormatType formatType;

    private List<Claim> claims;

    private UserSnapshot author;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.registrationDate = LocalDate.now();
        if (this.formatType == null) {
            this.formatType = FormatType.LITERATURE;
        }
        if (this.claims == null) {
            this.claims = new java.util.ArrayList<>();
        }
    }
}
