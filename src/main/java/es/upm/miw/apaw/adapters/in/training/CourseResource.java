package es.upm.miw.apaw.adapters.in.training;

import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.services.training.CourseService;
import jakarta.validation.Valid;
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

    @GetMapping(ID)
    public Course read(@PathVariable UUID id) {
        return this.courseService.read(id);
    }

    @PutMapping(ID)
    public Course update(@PathVariable UUID id, @Valid @RequestBody Course course) {
        return this.courseService.update(id, course);
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable UUID id) {
        this.courseService.delete(id);
    }
}
