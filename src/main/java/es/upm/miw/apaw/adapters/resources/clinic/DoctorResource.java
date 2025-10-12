package es.upm.miw.apaw.adapters.resources.clinic;

import es.upm.miw.apaw.domain.models.clinic.Doctor; // Importamos el Modelo de Dominio
import es.upm.miw.apaw.domain.services.clinic.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(DoctorResource.DOCTORS)
public class DoctorResource {

    public static final String DOCTORS = "/clinic/doctors";

    private final DoctorService doctorService;

    @Autowired
    public DoctorResource(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * GET /clinic/doctors
     * Devuelve directamente el Modelo de Dominio (Doctor)
     * @return Lista de Doctor
     */
    @GetMapping
    public List<Doctor> readAll() {
        return this.doctorService.readAll();
    }
}