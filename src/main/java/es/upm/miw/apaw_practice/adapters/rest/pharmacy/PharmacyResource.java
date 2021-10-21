package es.upm.miw.apaw_practice.adapters.rest.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import es.upm.miw.apaw_practice.domain.models.pharmacy.PharmacyDrugsUpdating;
import es.upm.miw.apaw_practice.domain.services.pharmacy.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PharmacyResource.PHARMACIES)
public class PharmacyResource {
    static final String PHARMACIES = "/pharmacy/pharmacies";

    private final PharmacyService pharmacyService;

    @Autowired
    public PharmacyResource(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @PostMapping
    public void createPharmacy(@RequestBody Pharmacy pharmacy) {
        this.pharmacyService.createPharmacy(pharmacy);
    }

    @PatchMapping
    public void updateDrugs(@RequestBody List<PharmacyDrugsUpdating> pharmacyDrugsUpdatingList) {
        this.pharmacyService.updateDrugs(pharmacyDrugsUpdatingList.stream());
    }
}
