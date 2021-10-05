package es.upm.miw.apaw_practice.adapters.rest.university;


import es.upm.miw.apaw_practice.domain.services.university.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ClassroomResource.CLASSROOMS)
public class ClassroomResource {
    static final String CLASSROOMS = "/university/classrooms";

    static final String CLASSROOM_ID = "/{school}/{number}"; //Preguntar esto

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomResource(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @DeleteMapping(CLASSROOM_ID)
    public void delete(@PathVariable String school, @PathVariable Integer number) {
        this.classroomService.delete(school, number);
    }
}
