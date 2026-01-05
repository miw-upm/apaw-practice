package es.upm.miw.apaw.adapters.resources.clinic;

import es.upm.miw.apaw.domain.models.clinic.Veterinarian;
import es.upm.miw.apaw.domain.services.clinic.VeterinarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VeterinarianResource.VETERINARIANS)
public class VeterinarianResource {

    public static final String VETERINARIANS = "/clinic/veterinarians";
    public static final String LICENSE = "/{licenseNumber}";

    private final VeterinarianService veterinarianService;

    @Autowired
    public VeterinarianResource(VeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

    @GetMapping(LICENSE)
    public Veterinarian readByLicense(@PathVariable Long licenseNumber) {
        return this.veterinarianService.readByLicense(licenseNumber);
    }

    @DeleteMapping(LICENSE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByLicense(@PathVariable Long licenseNumber) {
        this.veterinarianService.deleteByLicense(licenseNumber);
    }
}