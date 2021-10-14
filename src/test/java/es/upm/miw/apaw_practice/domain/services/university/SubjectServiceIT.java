package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.university.SubjectCreditsUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.SubjectPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestConfig
public class SubjectServiceIT {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectPersistence subjectPersistence;

    @Test
    void testUpdateCredits() {
        List<SubjectCreditsUpdating> subjectCreditsUpdatings = Arrays.asList(
                new SubjectCreditsUpdating(613000096, 6),
                new SubjectCreditsUpdating(613000095, 9)
        );
        this.subjectService.updateCredits(subjectCreditsUpdatings.stream());
        assertEquals(6, this.subjectPersistence.readByReference(613000096).getCredits());
        assertEquals(9, this.subjectPersistence.readByReference(613000095).getCredits());
    }
}
