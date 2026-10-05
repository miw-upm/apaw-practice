package es.upm.miw.apaw.adapters.out.judicialcourt.postgres;

import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JudicialCourtTypeEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(nullable = false, unique = true)
    private String code;

    private String jurisdiction;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<JudicialCourtEntity> judicialCourts;

    public JudicialCourtTypeEntity(JudicialCourtType type) {
        BeanUtils.copyProperties(type, this, "judicialCourts");
        this.active = type.getActive() == null || type.getActive();
    }

    public JudicialCourtType toDomain() {
        JudicialCourtType judicialCourtType = new JudicialCourtType();
        BeanUtils.copyProperties(this, judicialCourtType, "judicialCourts");
        return judicialCourtType;
    }
}
