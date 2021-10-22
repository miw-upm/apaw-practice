package es.upm.miw.apaw_practice.adapters.rest.Class;

import es.upm.miw.apaw_practice.domain.models.Class.Course;
import es.upm.miw.apaw_practice.domain.services.Class.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(CourseResource.theCourse)
public class CourseResource {

    static final String theCourse = "/class/courses";
    static final String theName = "/{name}";

    private final CourseService courseService;

    @Autowired
    public CourseResource(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public Stream<Course> readAll() {
        return this.courseService.readAll();
    }

    @DeleteMapping(theName)
    public void delete(@PathVariable String name) {
        this.courseService.delete(name);
    }
}
