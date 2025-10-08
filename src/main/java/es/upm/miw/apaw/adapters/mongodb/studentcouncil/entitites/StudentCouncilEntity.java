package es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class StudentCouncilEntity {

    @Id
    private UUID id;

    private String council;
    private String site;
    private BigDecimal resources;

    private List<RepresentativeEntity> representatives;
}