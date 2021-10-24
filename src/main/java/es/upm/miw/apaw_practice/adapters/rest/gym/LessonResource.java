package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Lesson;
import es.upm.miw.apaw_practice.domain.services.gym.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(LessonResource.Lessons)
public class LessonResource {
    static final String Lessons = "/gym/lesson";
    static final String Search = "/search";
    static final String TITLE = "/{title}";
    static final String NAME = "/Athlete/{name}";

    private final LessonService lessonService;

    @Autowired
    public LessonResource(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping(Search)
    public Lesson findByLabel(@RequestParam String title) {
        return this.lessonService.findBytitle(title);
    }


    @GetMapping(TITLE + NAME)
    public List<String> findGymBYTitleandName(@PathVariable("title") String title, @PathVariable("name") String name) {
        return this.lessonService.findGymByTitelAndName(title, name);
    }
}
