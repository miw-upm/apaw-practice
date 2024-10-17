package es.upm.miw.apaw_practice.adapters.rest.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import es.upm.miw.apaw_practice.domain.services.Hospital.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PatientResource.PATIENTS)
public class PatientResource {

    static final String PATIENTS = "/hospital/patients";

    private final PatientService patientService;

    @Autowired
    public PatientResource(PatientService patientService) {
        this.patientService = patientService;
    }

    @DeleteMapping("/{dni}")
    public void deletePatient(@PathVariable String dni) {
        patientService.delete(dni);
    }
    @PatchMapping(dni)
    public Patient updateName(@PathVariable String dni, @RequestBody String name) {
        return this.PatientService.updateName(dni,name);
    }

}
