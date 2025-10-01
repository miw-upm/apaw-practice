package es.upm.miw.apaw.domain.models.clothingstore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @NotNull
    private UUID invoiceId;

    @NotNull
    @NotBlank
    private String number;

    @NotNull
    private LocalDate issuedAt;
}
