package es.upm.miw.apaw.adapters.in.training;

import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.services.training.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(CourseResource.COURSES)
@RequiredArgsConstructor
public class CourseResource {
    public static final String COURSES = "/training/courses";
    public static final String ID = "/{id}";

    private final CourseService courseService;

    @PostMapping
    public Course create(@RequestBody Course course) {
        return this.courseService.create(course);
    }

    @GetMapping
    public Course read(UUID id) {
        return this.courseService.read(id);
    }
}
