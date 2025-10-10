package es.upm.miw.apaw.adapters.resources.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.Professor;
import es.upm.miw.apaw.domain.services.sports.academy.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProfessorResource.PROFESSORS)
public class ProfessorResource {
    public static final String PROFESSORS = "/sports-academy/professors";
    private final ProfessorService professorService;

    @Autowired
    public ProfessorResource(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public Professor createProfessor(@Valid @RequestBody Professor professor) {
        return this.professorService.create(professor);
    }
}
