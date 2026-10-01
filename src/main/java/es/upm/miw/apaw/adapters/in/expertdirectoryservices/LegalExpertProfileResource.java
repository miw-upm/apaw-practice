package es.upm.miw.apaw.adapters.in.expertdirectoryservices;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.services.expertdirectoryservices.LegalExpertProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES)
public class LegalExpertProfileResource {

    public static final String LEGAL_EXPERT_PROFILES = "/expertdirectory-services/legal-expert-profiles";

    private final LegalExpertProfileService legalExpertProfileService;

    @Autowired
    public LegalExpertProfileResource(LegalExpertProfileService legalExpertProfileService) {
        this.legalExpertProfileService = legalExpertProfileService;
    }

    @PostMapping
    public LegalExpertProfile create(@RequestBody LegalExpertProfile legalExpertProfile) {
        return this.legalExpertProfileService.create(legalExpertProfile);
    }
}