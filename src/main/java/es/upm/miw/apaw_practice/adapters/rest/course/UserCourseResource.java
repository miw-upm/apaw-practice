package es.upm.miw.apaw_practice.adapters.rest.course;

import es.upm.miw.apaw_practice.domain.models.course.User;
import es.upm.miw.apaw_practice.domain.services.course.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserCourseResource.USERS)
public class UserCourseResource {

    static final String USERS = "/course/user";

    private final UserCourseService userCourseService;

    @Autowired
    public UserCourseResource(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }

    @PostMapping
    public User create(@RequestBody User user){
        return this.userCourseService.create(user);
    }

    @GetMapping()
    public List<String> emailsOfTitleTutoringSession(@RequestParam String title){
        return this.userCourseService.emailsOfTitleTutoringSession(title);
    }
}
