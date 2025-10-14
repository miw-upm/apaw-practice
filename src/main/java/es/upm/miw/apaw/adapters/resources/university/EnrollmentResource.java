package es.upm.miw.apaw.adapters.resources.university;

import es.upm.miw.apaw.domain.services.university.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EnrollmentResource.ENROLLMENTS)
public class EnrollmentResource {
    public static final String ENROLLMENTS = "/enrollments";
    public static final String CODE_ID = "/{code}";
    
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentResource(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @DeleteMapping(CODE_ID)
    public void delete(@PathVariable String code) {
        this.enrollmentService.delete(code);
    }
}
