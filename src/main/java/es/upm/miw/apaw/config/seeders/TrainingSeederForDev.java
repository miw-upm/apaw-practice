package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.training.postgres.CourseEntity;
import es.upm.miw.apaw.adapters.out.training.postgres.CourseRepository;
import es.upm.miw.apaw.domain.model.training.Course;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class TrainingSeederForDev implements ApplicationRunner {
    public static final String PREFIX = "11111111-2222-3333-4444-55555555";
    public static final UUID COURSE_ID_0 = UUID.fromString(PREFIX + "0000");
    public static final Course COURSE_0 = new Course(
        COURSE_ID_0, "Spring Boot", "Ref-1", 40, true, LocalDate.of(2025, 1, 15)
    );
    public static final UUID COURSE_ID_1 = UUID.fromString(PREFIX + "0001");
    public static final Course COURSE_1 = new Course(
        COURSE_ID_1, "Angular", "Ref-2", 30, true, LocalDate.of(2025, 2, 20)
    );
    public static final UUID COURSE_ID_2 = UUID.fromString(PREFIX + "0002");
    public static final Course COURSE_2 = new Course(
        COURSE_ID_2, "Jenkins", "Ref-3", 15, false, LocalDate.of(2025, 3, 10)
    );
    public static final UUID COURSE_ID_3 = UUID.fromString(PREFIX + "0003");
    public static final Course COURSE_3 = new Course(
        COURSE_ID_3, "AWS Advanced", "Ref-4", 50, true, LocalDate.of(2025, 4, 1)
    );

    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.info("======== Seeding Training ========");
        this.seedCourses();
    }

    private void seedCourses() {
        if (this.courseRepository.count() == 0) {
            this.courseRepository.saveAll(List.of(
                new CourseEntity(COURSE_0),
                new CourseEntity(COURSE_1),
                new CourseEntity(COURSE_2),
                new CourseEntity(COURSE_3)
            ));
        }
    }
}
