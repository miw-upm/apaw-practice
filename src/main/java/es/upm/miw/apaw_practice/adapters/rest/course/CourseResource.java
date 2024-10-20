package es.upm.miw.apaw_practice.adapters.rest.course;

import es.upm.miw.apaw_practice.domain.models.course.Course;
import es.upm.miw.apaw_practice.domain.services.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CourseResource.COURSES)
public class CourseResource {

    static final String COURSES = "/course/course";
    static final String TITTLE = "/{tittle}";

    private final CourseService courseService;

    @Autowired
    public CourseResource(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(TITTLE)
    public Course read(@PathVariable String tittle){
        return this.courseService.read(tittle);
    }

}
