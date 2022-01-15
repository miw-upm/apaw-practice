package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.domain.models.training.Course;
import es.upm.miw.apaw_practice.domain.models.training.CoursePriceUpdating;
import es.upm.miw.apaw_practice.domain.services.training.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CourseResource.COURSES)
public class CourseResource {
    static final String COURSES = "/training/courses";

    private final CourseService courseService;

    @Autowired
    public CourseResource(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return this.courseService.create(course);
    }

    @PatchMapping
    public void updatePrices(@RequestBody List<CoursePriceUpdating> coursePriceUpdatingList) {
        this.courseService.updatePrices(coursePriceUpdatingList.stream());
    }
}