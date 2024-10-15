package es.upm.miw.apaw_practice.adapters.rest.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import es.upm.miw.apaw_practice.domain.services.Hospital.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(HospitalResource.HOSPITALS)
public class HospitalResource {

    static final String HOSPITALS = "/hospital";

    private final HospitalService hospitalService;

    @Autowired
    public HospitalResource(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    // Endpoint to create a hospital
    @PostMapping
    public Hospital createHospital(@RequestBody Hospital hospital) {
        return this.hospitalService.createHospital(hospital);
    }
}
