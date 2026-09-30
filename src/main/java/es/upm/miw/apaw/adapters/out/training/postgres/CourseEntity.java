package es.upm.miw.apaw.adapters.out.training.postgres;

import es.upm.miw.apaw.domain.model.training.Course;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
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
public class CourseEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true)
    private String certificateReference;

    @Column(nullable = false)
    private Integer durationHours;

    @Column
    private Boolean online;

    @Column
    private LocalDate launchDate;

    public CourseEntity(Course course) {
        BeanUtils.copyProperties(course, this);
    }

    public Course toDomain() {
        Course course = new Course();
        BeanUtils.copyProperties(this, course);
        return course;
    }
}
