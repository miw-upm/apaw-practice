package es.upm.miw.apaw.adapters.resources.clinic;

import es.upm.miw.apaw.domain.models.clinic.Doctor;
import es.upm.miw.apaw.domain.services.clinic.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(DoctorResource.DOCTORS)
public class DoctorResource {

    public static final String DOCTORS = "/doctors";
    public static final String LICENSE_NUMBER = "/{licenseNumber}";

    private final DoctorService doctorService;

    @Autowired
    public DoctorResource(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Doctor create(@Valid @RequestBody Doctor doctor) {
        return this.doctorService.create(doctor);
    }

    @GetMapping
    public List<Doctor> readAll() {
        return this.doctorService.readAll();
    }

    @GetMapping(LICENSE_NUMBER)
    public Doctor readByLicenseNumber(@PathVariable Long licenseNumber) {
        return this.doctorService.readByLicenseNumber(licenseNumber);
    }

    @PutMapping(LICENSE_NUMBER)
    @ResponseStatus(HttpStatus.OK)
    public Doctor update(@PathVariable Long licenseNumber, @Valid @RequestBody Doctor doctor) {
        return this.doctorService.update(licenseNumber, doctor);
    }

    @PatchMapping(LICENSE_NUMBER)
    public Doctor patch(@PathVariable Long licenseNumber, @RequestBody Map<String, Object> updates) {
        return this.doctorService.patch(licenseNumber, updates);
    }

    @DeleteMapping(LICENSE_NUMBER)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long licenseNumber) {
        this.doctorService.delete(licenseNumber);
    }

    @ExceptionHandler({ConflictException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleConflictException() {}

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException() {}
}