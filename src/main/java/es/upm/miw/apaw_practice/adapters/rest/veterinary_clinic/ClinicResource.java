package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Clinic;
import es.upm.miw.apaw_practice.domain.services.veterinary_clinic.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ClinicResource.CLINICS)
public class ClinicResource {

    static final String CLINICS = "/veterinary-clinic/clinics";
    static final String NAME_ID = "/{name}";
    static final String SEARCH = "/search";

    private final ClinicService clinicService;

    @Autowired
    public ClinicResource(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(NAME_ID)
    public Clinic read(@PathVariable String name) {
        return Clinic.ofEmployeeName(this.clinicService.read(name));
    }
}