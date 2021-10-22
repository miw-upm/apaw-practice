package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.hospital.Disease;
import es.upm.miw.apaw_practice.domain.models.hospital.DiseaseUpdate;
import es.upm.miw.apaw_practice.domain.services.hospital.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(DiseaseResource.DISEASES)
public class DiseaseResource {

    static final String DISEASES = "/hospital/diseases";
    static final String SEARCH = "/search";

    private final DiseaseService diseaseService;

    @Autowired
    public DiseaseResource(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @PatchMapping
    public void updateDescription(@RequestBody List<DiseaseUpdate> diseaseUpdates){
        this.diseaseService.updateDiseases(diseaseUpdates);
    }

    @GetMapping(SEARCH)
    public Stream<Disease> findAliasByDoctorNick(@RequestParam String q){
        String nick = new LexicalAnalyzer().extractWithAssure(q,"doctorNick");
        return this.diseaseService.findAliasByDoctorNick(nick);
    }

}
