package es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites;

import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Document
public class StudentCouncilEntity {

    @Id
    private UUID id;

    private String council;
    private String site;
    private BigDecimal resources;

    @DBRef
    private List<RepresentativeEntity> representatives;


    public StudentCouncil toStudentCouncil() {
        return StudentCouncil.builder()
                .id(this.id)
                .council(this.council)
                .site(this.site)
                .resources(this.resources)
                .representatives(this.representatives == null ? null :
                        this.representatives.stream().map(RepresentativeEntity::toRepresentative).toList())
                .build();
    }

    public void fromStudentCouncil(StudentCouncil studentCouncil) {
        this.council = studentCouncil.getCouncil();
        this.site = studentCouncil.getSite();
        this.resources = studentCouncil.getResources();
    }
}