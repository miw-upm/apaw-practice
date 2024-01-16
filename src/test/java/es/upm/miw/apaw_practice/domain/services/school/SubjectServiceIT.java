package es.upm.miw.apaw_practice.domain.services.school;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.school.SchoolSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.school.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class SubjectServiceIT {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SchoolSeederService schoolSeederService;

    @AfterEach
    void resetDataBase() {
        this.schoolSeederService.deleteAll();
        this.schoolSeederService.seedDatabase();
    }

    @Test
    void testRead() {
        Subject subject = this.subjectService.read("subject1");
        assertEquals("subject1", subject.getTitle());
        assertEquals("desc1", subject.getDescription());
        assertTrue(subject.getBilingual());
        assertEquals(6, subject.getCredits());
    }

    @Test
    void testCreate() {
        Subject subject = new Subject("test", "desc", true, 10);
        this.subjectService.create(subject);
        Subject savedSubject = this.subjectService.read("test");
        assertEquals("test" , savedSubject.getTitle());
        assertTrue(savedSubject.getBilingual());
    }

    @Test
    void testDelete() {
        assertEquals("subject1", this.subjectService.read("subject1").getTitle());
        this.subjectService.delete("subject1");
        assertThrows(NotFoundException.class, () -> this.subjectService.read("subject1"));
    }


    @Test
    void testFindUniqueDescriptionBySmartBoard() {
        assertEquals(List.of("desc1", "desc2", "desc3", "desc4", "desc5", "descSearch2", "descSearch3"),
            this.subjectService.findUniqueDescriptionBySmartBoard(true));
        assertEquals(List.of("desc2", "descSearch1", "descSearch3"),
            this.subjectService.findUniqueDescriptionBySmartBoard(false));
    }
}
