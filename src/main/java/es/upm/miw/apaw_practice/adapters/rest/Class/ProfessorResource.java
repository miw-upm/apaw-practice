package es.upm.miw.apaw_practice.adapters.rest.Class;

import es.upm.miw.apaw_practice.domain.models.Class.Professor;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import es.upm.miw.apaw_practice.domain.services.Class.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(ProfessorResource.theProfessor)
public class ProfessorResource {
    static final String theProfessor = "/class/professor";

    private final ProfessorService professorService;

    @Autowired
    public ProfessorResource(ProfessorService professorService){
        this.professorService = professorService;
    }

    @GetMapping
    public Stream<Professor> readAll(){return this.professorService.readAll();}

    @PostMapping
    public Professor create(@RequestBody Professor professor) {
        return this.professorService.create(professor);
    }
}
