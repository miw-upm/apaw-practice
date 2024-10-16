package es.upm.miw.apaw_practice.adapters.rest.Hospital;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.DoctorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorResource {

    private final DoctorPersistence doctorPersistence;

    @Autowired
    public DoctorResource(DoctorPersistence doctorPersistence) {
        this.doctorPersistence = doctorPersistence;
    }

    @PutMapping("/{dni}")
    public Doctor updateDoctor(@PathVariable String dni, @RequestBody Doctor updatedDoctor) {
        return this.doctorPersistence.update(dni, updatedDoctor);
    }

}
