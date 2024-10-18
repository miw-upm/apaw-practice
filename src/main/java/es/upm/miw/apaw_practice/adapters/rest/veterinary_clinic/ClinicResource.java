package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Clinic;
import es.upm.miw.apaw_practice.domain.services.veterinary_clinic.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping(ClinicResource.CLINICS)
public class ClinicResource {

    static final String CLINICS = "/veterinary-clinic/clinics";
    static final String NAME_ID = "/{name}";
    static final String SEARCH = "/search";
    static final String TOTAL_SUM_OF_AGE = "/total-sum-of-age";

    private final ClinicService clinicService;

    @Autowired
    public ClinicResource(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(NAME_ID)
    public Clinic read(@PathVariable String name) {
        return Clinic.ofEmployeeName(this.clinicService.read(name));
    }

    @DeleteMapping(NAME_ID)
    public void delete(@PathVariable String name) {
        this.clinicService.delete(name);
    }

    @GetMapping(SEARCH + TOTAL_SUM_OF_AGE)
    public Mono<BigDecimal> findByOwnerNameSumAge(@RequestParam String q) {
        String clinicName = new LexicalAnalyzer().extractWithAssure(q, "clinicName").trim();
        String ownerName = new LexicalAnalyzer().extractWithAssure(q, "ownerName").trim();
        return this.clinicService.findByOwnerNameSumAge(clinicName, ownerName);
    }

    @GetMapping(SEARCH)
    public Flux<String> findByOwnerPhoneDistincClinicName(@RequestParam String q) {
        String phone = new LexicalAnalyzer().extractWithAssure(q, "phone").trim();
        return this.clinicService.findByOwnerPhoneDistincClinicName(phone);
    }
}