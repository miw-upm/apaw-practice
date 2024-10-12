package es.upm.miw.apaw_practice.adapters.rest.theme_park;

import es.upm.miw.apaw_practice.domain.models.theme_park.User;
import es.upm.miw.apaw_practice.domain.services.theme_park.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    static final String USERS = "/users";
    public static final String ID_MEMBERSHIP = "/{idMembership}";

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return this.userService.create(user);
    }

    @PutMapping(ID_MEMBERSHIP)
    public User update(@PathVariable String idMembership, @RequestBody User user) {
        this.userService.assertIdMembershipNotExist(idMembership);
        return userService.update(idMembership, user);
    }
}
