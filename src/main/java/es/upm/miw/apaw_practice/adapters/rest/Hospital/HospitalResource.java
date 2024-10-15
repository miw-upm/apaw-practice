package es.upm.miw.apaw_practice.adapters.rest.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import es.upm.miw.apaw_practice.domain.services.Hospital.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(HospitalResource.HOSPITALS)
public class HospitalResource {

    static final String HOSPITALS = "/hospital/hospitals";

    private final HospitalService hospitalService;

    @Autowired
    public HospitalResource(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    // POST endpoint to create a new hospital
    @PostMapping
    public Hospital create(@RequestBody Hospital hospital) {
        return this.hospitalService.create(hospital);
    }
}
