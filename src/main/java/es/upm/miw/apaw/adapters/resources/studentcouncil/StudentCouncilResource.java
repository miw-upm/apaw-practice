package es.upm.miw.apaw.adapters.resources.studentcouncil;

import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;
import es.upm.miw.apaw.domain.services.studentcouncil.StudentCouncilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping(StudentCouncilResource.STUDENT_COUNCILS)
public class StudentCouncilResource {

    public static final String STUDENT_COUNCILS = "/student-councils";
    public static final String ID_ID = "/{id}";

    private final StudentCouncilService studentCouncilService;

    @Autowired
    public StudentCouncilResource(StudentCouncilService studentCouncilService) {
        this.studentCouncilService = studentCouncilService;
    }

    @PatchMapping(ID_ID)
    public StudentCouncil updateResources(@PathVariable UUID id, @RequestBody BigDecimal newResources) {
        return this.studentCouncilService.updateResources(id, newResources);
    }
}