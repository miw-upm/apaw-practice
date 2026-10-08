package es.upm.miw.apaw.adapters.out.invoice.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.invoice.Invoice;
import es.upm.miw.apaw.domain.model.invoice.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InvoiceEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String invoiceNumber;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private BigDecimal taxableBase;

    @Column(nullable = false)
    private BigDecimal vatRate;

    @Column(nullable = false)
    private Boolean paid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<LegalServiceEntity> services;

    @Column(nullable = false)
    private UUID userId;

    public InvoiceEntity(Invoice invoice) {
        BeanUtils.copyProperties(invoice, this, "services", "customer");

        this.services = invoice.getServices().stream()
                .map(LegalServiceEntity::new)
                .toList();

        this.userId = invoice.getCustomer().getId();
    }

    public Invoice toDomain() {
        Invoice invoice = new Invoice();

        BeanUtils.copyProperties(this, invoice, "services", "userId");

        invoice.setServices(new ArrayList<>(this.services.stream()
                .map(LegalServiceEntity::toDomain)
                .toList()));

        invoice.setCustomer(UserSnapshot.builder()
                .id(this.userId)
                .build());

        return invoice;
    }
}