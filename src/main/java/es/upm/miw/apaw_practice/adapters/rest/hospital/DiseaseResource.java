package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.DiseaseUpdate;
import es.upm.miw.apaw_practice.domain.services.hospital.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(DiseaseResource.DISEASES)
public class DiseaseResource {

    static final String DISEASES = "/hospital/diseases";

    private final DiseaseService diseaseService;

    @Autowired
    public DiseaseResource(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @PatchMapping
    public void updateDescription(@RequestBody List<DiseaseUpdate> diseaseUpdates){
        this.diseaseService.updateDiseases(diseaseUpdates);
    }

}
