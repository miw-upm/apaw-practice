package es.upm.miw.apaw_practice.domain.models.university;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class SubjectComponentTest {

    private SubjectComponent subjectIwvg;
    private SubjectComponent subjectFem;
    private SubjectComponent subjectArea;

    @BeforeEach
    void before() {
        Classroom classroom = Classroom.builder().school("ETSISI").number(1302).capacity(20).build();
        this.subjectIwvg = new SingleSubject(new Subject(613000095, "Ingeniería Web: Visión General", 6, classroom, new ArrayList<>()));
        this.subjectFem = new SingleSubject(new Subject(613000097, "Front-end para Móviles", 4, classroom, new ArrayList<>()));
        this.subjectArea = new SubjectArea("Ingeniería Web");
    }

    @Test
    void testAddArea() {
        this.subjectArea.add(this.subjectIwvg);
        SubjectComponent subjectAreaChild = new SubjectArea("Tecnologías Web para Móviles");
        subjectAreaChild.add(this.subjectFem);
        this.subjectArea.add(subjectAreaChild);
        assertEquals(this.subjectArea.get(0).getDescription(), "Ingeniería Web: Visión General");
        assertEquals(this.subjectArea.get(1).getDescription(), "Tecnologías Web para Móviles");
        assertEquals(subjectAreaChild.get(0).getDescription(), "Front-end para Móviles");
    }

    @Test
    void testRemoveArea() {
        this.subjectArea.add(this.subjectIwvg);
        assertEquals(this.subjectArea.get(0).getDescription(), "Ingeniería Web: Visión General");
        this.subjectArea.remove(this.subjectIwvg);
        assertThrows(IndexOutOfBoundsException.class, () -> this.subjectArea.get(0));
    }

    @Test
    void testAddSingle() {
        assertThrows(UnsupportedOperationException.class, () -> this.subjectIwvg.add(this.subjectFem));
    }

    @Test
    void testRemoveSingle() {
        assertThrows(UnsupportedOperationException.class, () -> this.subjectIwvg.remove(this.subjectFem));
    }

    @Test
    void testGetSingle() {
        assertThrows(UnsupportedOperationException.class, () -> this.subjectIwvg.get(0));
    }

    @Test
    void testIsArea() {
        assertFalse(this.subjectIwvg.isArea());
        assertTrue(this.subjectArea.isArea());
    }
}
