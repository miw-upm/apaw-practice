package es.upm.miw.apaw.domain.model.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.Valid;
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
    private UUID id;

    @NotNull
    private String invoiceNumber;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    private LocalDate issueDate;

    @NotNull
    private BigDecimal taxableBase;

    @NotNull
    private BigDecimal vatRate;

    @NotNull
    private Boolean paid;

    @NotNull
    private PaymentType paymentType;

    @Valid
    private List<@Valid LegalService> services;

    @NotNull
    @Valid
    private UserSnapshot customer;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.issueDate = LocalDate.now();
        this.invoiceNumber = "INV-" + this.issueDate.toString().replace("-", "")
                + "-" + this.id.toString().substring(0, 8);
        this.paid = false;
        this.paymentType = PaymentType.CASH;
        this.services = new ArrayList<>();
    }
}