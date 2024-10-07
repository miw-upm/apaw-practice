package es.upm.miw.apaw_practice.adapters.rest.university;


import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.university.Degree;
import es.upm.miw.apaw_practice.domain.services.university.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping(DegreeResource.DEGREES)
public class DegreeResource {
    static final String DEGREES = "/university/degrees";

    static final String CODE_ID = "/{code}";

    static final String SEARCH = "/search";

    private final DegreeService degreeService;

    @Autowired
    public DegreeResource(DegreeService degreeService) {
        this.degreeService = degreeService;
    }

    @GetMapping(CODE_ID)
    public Degree read(@PathVariable Integer code) {
        return degreeService.read(code);
    }

    @GetMapping(SEARCH)
    public Stream<Degree> searchByCapacityBetween(@RequestParam Optional<String> q) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        int minCapacity;
        int maxCapacity;
        if (q.isEmpty()) {
            throw new BadRequestException("q parameter expected but not sent.");
        }
        try {
            minCapacity = Integer.parseInt(lexicalAnalyzer.extractWithAssure(q.get(), "minCapacity"));
        } catch (NumberFormatException e) {
            throw new BadRequestException("q incorrect, minCapacity expects an integer.");
        }
        try {
            maxCapacity = Integer.parseInt(lexicalAnalyzer.extractWithAssure(q.get(), "maxCapacity"));
        } catch (NumberFormatException e) {
            throw new BadRequestException("q incorrect, minCapacity expects an integer.");
        }
        return degreeService.findByCapacityBetween(minCapacity, maxCapacity);
    }
}
