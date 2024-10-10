package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.university.University;
import es.upm.miw.apaw_practice.domain.services.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UniversityResource.UNIVERSITIES)
public class UniversityResource {
    public static final String UNIVERSITIES = "/universities";

    private final UniversityService universityService;

    @Autowired
    public UniversityResource(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping()
    public ResponseEntity<Void> post(@RequestBody University university) {
        if (universityService.existsTopDomain(university.getTopDomain())) {
            throw new BadRequestException("Top domain '" + university.getTopDomain() + "' already exists.");
        }
        universityService.create(university);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
