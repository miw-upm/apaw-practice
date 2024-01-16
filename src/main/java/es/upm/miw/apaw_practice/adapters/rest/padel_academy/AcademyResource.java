package es.upm.miw.apaw_practice.adapters.rest.padel_academy;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.padel_academy.Academy;
import es.upm.miw.apaw_practice.domain.services.padel_academy.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AcademyResource.ACADEMIES)
public class AcademyResource {
    static final String ACADEMIES = "/padel-academy/academies";
    static final String NAME = "/{name}";
    static final String ADDRESS = "/address";

    static final String SEARCH = "/search";
    private final AcademyService academyService;

    @Autowired
    public AcademyResource(AcademyService academyService) {
        this.academyService = academyService;
    }

    @GetMapping(NAME)
    public Academy read(@PathVariable String name) {return this.academyService.read(name);}

    @DeleteMapping(NAME)
    public void delete(@PathVariable String name) {this.academyService.delete(name);}

    @PutMapping(NAME + ADDRESS)
    public Academy updateAddress(@PathVariable String name, @RequestBody String address) {
        return this.academyService.updateAddress(name, address);
    }

    @GetMapping(SEARCH)
    public List<String> findAcademyAddressByInstructorName(@RequestParam String q) {
        String name = new LexicalAnalyzer().extractWithAssure(q, "name", String::new);
        return this.academyService.findAcademyAddressByInstructorName(name);
    }
}
