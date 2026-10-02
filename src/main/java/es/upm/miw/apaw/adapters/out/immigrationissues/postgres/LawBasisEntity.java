package es.upm.miw.apaw.adapters.out.immigrationissues.postgres;

import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LawBasisEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String lawCode;

    @Column(nullable = false)
    private String lawName;

    @Column(nullable = false)
    private Integer articleNumber;

    @Column(nullable = false)
    private LocalDate publishedOn;

    @Column(nullable = false)
    private Boolean active;

    public LawBasisEntity(LawBasis lawBasis) {
        BeanUtils.copyProperties(lawBasis, this);
    }

    public LawBasis toDomain() {
        LawBasis lawBasis = new LawBasis();
        BeanUtils.copyProperties(this, lawBasis);
        return lawBasis;
    }
}