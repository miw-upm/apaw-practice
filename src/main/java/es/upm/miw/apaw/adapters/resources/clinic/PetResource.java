package es.upm.miw.apaw.adapters.resources.clinic;

import es.upm.miw.apaw.domain.models.clinic.Pet;
import es.upm.miw.apaw.domain.services.clinic.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PetResource.PETS)
public class PetResource {

    public static final String PETS = "/clinic/pets";
    public static final String MICROCHIP = "/{microchipNumber}";
    public static final String MICROCHIPS_BY_VETERINARIAN_LICENSE_NUMBER = "/veterinarian/{licenseNumber}/microchip-numbers";

    private final PetService petService;

    @Autowired
    public PetResource(PetService petService) {
        this.petService = petService;
    }

    @PutMapping(MICROCHIP)
    public Pet update(@PathVariable Long microchipNumber, @Valid @RequestBody Pet pet) {
        return this.petService.update(microchipNumber, pet);
    }

    @GetMapping(MICROCHIPS_BY_VETERINARIAN_LICENSE_NUMBER)
    public List<Long> findMicrochipNumbersByLicenseNumber(@PathVariable Long licenseNumber) {
        return this.petService.findMicrochipNumbersByLicenseNumber(licenseNumber);
    }
}