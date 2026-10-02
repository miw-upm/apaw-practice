package es.upm.miw.apaw.adapters.in.training;

import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.model.training.CourseDurationUpdate;
import es.upm.miw.apaw.domain.services.training.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.courseService.delete(id);
    }

    @GetMapping
    public List<Course> findAll() {
        return this.courseService.findAll();
    }

    @PatchMapping
    public void updateDurationHours(
            @RequestBody @NotEmpty List<@NotNull @Valid CourseDurationUpdate> updates) {
        this.courseService.updateDurationHours(updates);
    }

    @org.springframework.web.bind.annotation.GetMapping("/modality-report")
    public java.util.List<es.upm.miw.apaw.domain.model.training.TrainingModalityReport> findModalityReport() {
        return this.courseService.findModalityReport();
    }
}