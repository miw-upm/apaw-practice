package es.upm.miw.apaw_practice.adapters.rest.pharmacy;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Dispensing;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import es.upm.miw.apaw_practice.domain.models.pharmacy.PharmacyDrugsUpdating;
import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;
import es.upm.miw.apaw_practice.domain.models.shop.Tag;
import es.upm.miw.apaw_practice.domain.services.pharmacy.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(PharmacyResource.PHARMACIES)
public class PharmacyResource {
    static final String PHARMACIES = "/pharmacy/pharmacies";

    static final String SEARCH = "/search";

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

    @GetMapping(SEARCH)
    public Stream<Pharmacy> findByDispensing(@RequestParam String q) {
        String dispensing = new LexicalAnalyzer().extractWithAssure(q, "dispensing");
        return this.pharmacyService.findByDispensing(dispensing);
    }
}
