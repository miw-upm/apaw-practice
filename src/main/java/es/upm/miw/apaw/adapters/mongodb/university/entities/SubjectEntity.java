package es.upm.miw.apaw.adapters.mongodb.university.entities;

import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.models.university.Subject;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class SubjectEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String name;
    private String description;
    private Integer credits;

    public SubjectEntity(Subject subject) {
        BeanUtils.copyProperties(subject, this);
        this.id = UUID.randomUUID();
    }

    public Subject toSubject() {
        Subject subject = new Subject();
        BeanUtils.copyProperties(this, subject);
        return subject;
    }
}
