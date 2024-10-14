package es.upm.miw.apaw_practice.adapters.rest.music_lesson;

import java.util.List;

import es.upm.miw.apaw_practice.domain.models.music_lesson.Learner;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Lesson;
import es.upm.miw.apaw_practice.domain.services.music_lesson.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LearnerResource.LEARNERS)
public class LearnerResource {

  static final String LEARNERS = "/music-lesson/learners";

  static final String IDENTITY_DOCUMENT_ID = "/{identityDocument}";

  static final String LESSONS = "/lessons";

  private final LearnerService learnerService;

  @Autowired
  public LearnerResource(LearnerService learnerService) {
    this.learnerService = learnerService;
  }

  @DeleteMapping(IDENTITY_DOCUMENT_ID)
  public void delete(@PathVariable String identityDocument) {
    this.learnerService.delete(identityDocument);
  }

  @PutMapping(IDENTITY_DOCUMENT_ID + LESSONS)
  public Learner updateLessons(@PathVariable String identityDocument, @RequestBody List<Lesson> lessons) {
    return this.learnerService.updateLessons(identityDocument, lessons);
  }

}
