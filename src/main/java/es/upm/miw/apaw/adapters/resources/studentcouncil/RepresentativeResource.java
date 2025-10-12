package es.upm.miw.apaw.adapters.resources.studentcouncil;

import es.upm.miw.apaw.domain.models.studentcouncil.Representative;
import es.upm.miw.apaw.domain.services.studentcouncil.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/representatives")
public class RepresentativeResource {

    private final RepresentativeService representativeService;

    @Autowired
    public RepresentativeResource(RepresentativeService representativeService) {
        this.representativeService = representativeService;
    }

    @GetMapping
    public List<Representative> getAll() {
        return this.representativeService.getAllRepresentatives();
    }
}