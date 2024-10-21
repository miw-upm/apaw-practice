package es.upm.miw.apaw_practice.adapters.rest.martial_art;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import es.upm.miw.apaw_practice.domain.services.martial_art.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(InstructorResource.INSTRUCTOR)
public class InstructorResource {
    static final String INSTRUCTOR = "/martial_art/instructor";
    static final String DNI = "/{dni}";

    static final String PHONE = "/phone";
      private final InstructorService instructorService;

    @Autowired
    public InstructorResource(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public Instructor create(@RequestBody Instructor instructor) {
        return this.instructorService.create(instructor);
    }

    @GetMapping(DNI)
    public Instructor read(@PathVariable String dni) {
        return this.instructorService.read(dni);
    }

    @DeleteMapping(DNI)
    public void delete(@PathVariable String dni) {
        this.instructorService.delete(dni);
    }

    @PutMapping(DNI)
    public Instructor update(@PathVariable String dni, @RequestBody Instructor instructor) {
        return this.instructorService.update(dni, instructor);
    }
    @PatchMapping(DNI + PHONE)
    public Instructor updatePhone(@PathVariable String dni, @RequestBody Integer phone) {
        return this.instructorService.updatePhoneNumber(dni, phone);
    }
}
