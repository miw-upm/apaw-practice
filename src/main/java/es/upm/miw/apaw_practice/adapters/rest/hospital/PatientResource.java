package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.Patient;
import es.upm.miw.apaw_practice.domain.services.hospital.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PatientResource.PATIENTS)
public class PatientResource {

    static final String PATIENTS = "/hospital/patients";

    static final String DNI_ID = "/{dni}";

    private final PatientService patientService;

    @Autowired
    public PatientResource(PatientService patientService) {
        this.patientService = patientService;
    }

    @DeleteMapping(DNI_ID)
    public void delete(@PathVariable String dni) {
        this.patientService.delete(dni);
    }

    @PutMapping(DNI_ID)
    public Patient update(@PathVariable String dni, @RequestBody Patient patient) {
        return this.patientService.update(dni, patient);
    }
}
