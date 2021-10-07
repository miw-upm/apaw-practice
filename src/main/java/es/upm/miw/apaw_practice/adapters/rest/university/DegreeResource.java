package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.domain.services.university.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(DegreeResource.DEGREES)
public class DegreeResource {

    static final String DEGREES = "/university/degrees";

    private final DegreeService degreeService;

    @Autowired
    public DegreeResource(DegreeService degreeService) {
        this.degreeService = degreeService;
    }

    @GetMapping
    public Stream<String> read() {
        return this.degreeService.read();
    }
}
