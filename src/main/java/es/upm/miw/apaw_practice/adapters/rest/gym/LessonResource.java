package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Lesson;
import es.upm.miw.apaw_practice.domain.services.gym.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(LessonResource.Lessons)
public class LessonResource {
    static final String Lessons = "/gym/lesson";
    static final String Search = "/search";

    private final LessonService lessonService;

    @Autowired
    public LessonResource(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping(Search)
    public Lesson findByLabel(@RequestParam String title) {
        return this.lessonService.findBytitle(title);
    }


}
