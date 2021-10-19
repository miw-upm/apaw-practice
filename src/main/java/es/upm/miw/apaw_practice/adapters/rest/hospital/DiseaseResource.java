package es.upm.miw.apaw_practice.adapters.rest.hospital;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DiseaseResource.DISEASES)
public class DiseaseResource {

    static final String DISEASES = "/hospital/diseases";

}
