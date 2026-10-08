package es.upm.miw.apaw.adapters.out.invoice.postgres;

import es.upm.miw.apaw.domain.model.invoice.LegalArea;
import es.upm.miw.apaw.domain.model.invoice.LegalService;
import es.upm.miw.apaw.domain.model.invoice.ServiceCategory;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LegalServiceEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal fee;

    @Column(nullable = false)
    private Boolean requiresAppointment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LegalArea legalArea;

    public LegalServiceEntity(LegalService legalService) {
        BeanUtils.copyProperties(legalService, this);
    }

    public LegalService toDomain() {
        LegalService legalService = new LegalService();
        BeanUtils.copyProperties(this, legalService);
        return legalService;
    }
}