package es.upm.miw.apaw_practice.domain.models.music_lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeLessonTest {

  private  TreeLesson lessonRoot;

  private TreeLesson lessonLeaf;

  @BeforeEach
  void init(){
    List<MusicalInstrument> musicalInstrumentsRoot = List.of(new MusicalInstrument("XX-DD","Beginner","Piano"));
    this.lessonRoot = new TreeLessonComposite(LocalDate.now(), 2, BigDecimal.valueOf(18.19),musicalInstrumentsRoot);

    List<MusicalInstrument> musicalInstrumentsLeaf = List.of(new MusicalInstrument("XX-DD-Q","Beginner","Piano"));
    Lesson lesson = new Lesson(LocalDate.now(), 3, BigDecimal.valueOf(18.17),musicalInstrumentsLeaf);
    this.lessonLeaf = new TreeLessonLeaf(lesson);

    this.lessonRoot.add(this.lessonLeaf);
  }

  @Test
  void testLeafLessonIfComposite(){
    assertFalse(lessonRoot.getLeafLessons().isEmpty());
    TreeLesson lessonChild =  lessonRoot.getLeafLessons().get(0);

    assertFalse(lessonChild.isComposite());
    assertEquals(3, lessonChild.getDurationInHours());
    assertEquals(BigDecimal.valueOf(18.17), lessonChild.getFee());
    assertFalse(lessonChild.getMusicalInstruments().isEmpty());
    assertEquals("XX-DD-Q", lessonChild.getMusicalInstruments().get(0).getModel());
  }

  @Test
  void testLeafLessonIfLeaf(){
    assertTrue(lessonLeaf.getLeafLessons().isEmpty());
  }

  @Test
  void testLessonDetailsIfComposite(){
    assertTrue(lessonRoot.isComposite());
    assertEquals(2, lessonRoot.getDurationInHours());
    assertEquals(BigDecimal.valueOf(18.19), lessonRoot.getFee());
    assertFalse(lessonRoot.getMusicalInstruments().isEmpty());
    assertEquals("XX-DD", lessonRoot.getMusicalInstruments().get(0).getModel());
  }

  @Test
  void testAddLeaf_throwsUnsupportedOperationException() {
    Lesson lesson = new Lesson(LocalDate.now(), 3, BigDecimal.valueOf(18.17), Collections.emptyList());
    Assertions.assertThrows(UnsupportedOperationException.class, () -> this.lessonLeaf.add(new TreeLessonLeaf(lesson)));
  }

}
