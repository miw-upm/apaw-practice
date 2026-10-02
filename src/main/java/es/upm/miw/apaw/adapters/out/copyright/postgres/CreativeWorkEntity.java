package es.upm.miw.apaw.adapters.out.copyright.postgres;

import es.upm.miw.apaw.domain.model.copyright.FormatType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreativeWorkEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String registrationCode;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal estimatedValuation;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @Column(nullable = false)
    private String authorPenName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormatType formatType;

    @Column(nullable = false)
    private UUID userId;
}
