package es.upm.miw.apaw.domain.models.clothingstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @NotNull
    private UUID invoiceId;

    @NotBlank
    private String number;

    @NotNull
    private LocalDate issuedAt;
}

