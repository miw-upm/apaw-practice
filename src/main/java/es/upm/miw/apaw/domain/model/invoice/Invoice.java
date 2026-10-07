package es.upm.miw.apaw.domain.model.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Invoice {

    @EqualsAndHashCode.Include
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotNull
    @Builder.Default
    private String invoiceNumber = generateInvoiceNumber();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @Builder.Default
    private LocalDate issueDate = LocalDate.now();

    @NotNull
    private BigDecimal taxableBase;

    @NotNull
    private BigDecimal vatRate;

    @NotNull
    @Builder.Default
    private Boolean paid = false;

    @NotNull
    @Builder.Default
    private PaymentType paymentType = PaymentType.CASH;

    @NotEmpty
    @Valid
    @Builder.Default
    private List<@Valid LegalService> services = new ArrayList<>();

    @NotNull
    @Valid
    private UserSnapshot customer;

    private static String generateInvoiceNumber() {
        return "INV-" + LocalDate.now().toString().replace("-", "")
                + "-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
