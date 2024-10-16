package es.upm.miw.apaw_practice.adapters.rest.course;

import es.upm.miw.apaw_practice.domain.services.theme_park.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserCourseResource.USERS)
public class UserCourseResource {

    static final String USERS = "/course/users";

    private final UserService userService;

    @Autowired

    public UserCourseResource(UserService userService) {
        this.userService = userService;
    }
}
