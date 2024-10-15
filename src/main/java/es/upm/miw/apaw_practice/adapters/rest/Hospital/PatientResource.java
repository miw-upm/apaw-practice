package es.upm.miw.apaw_practice.adapters.rest.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import es.upm.miw.apaw_practice.domain.services.Hospital.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PatientResource.PATIENTS)
public class PatientResource {

    static final String PATIENTS = "/hospital/patients";

    private final PatientService patientService;

    @Autowired
    public PatientResource(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> findAll() {
        return this.patientService.findAll();
    }

    // Endpoint para eliminar un paciente por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.patientService.delete(id);
    }
}
