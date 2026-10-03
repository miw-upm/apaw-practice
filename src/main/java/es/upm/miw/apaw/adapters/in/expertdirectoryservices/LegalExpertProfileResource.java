package es.upm.miw.apaw.adapters.in.expertdirectoryservices;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.services.expertdirectoryservices.LegalExpertProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Stream;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES)
public class LegalExpertProfileResource {

    public static final String LEGAL_EXPERT_PROFILES = "/expert-directory-services/legal-expert-profiles";
    public static final String ID_ID = "/{id}";
    private final LegalExpertProfileService legalExpertProfileService;

    @Autowired
    public LegalExpertProfileResource(LegalExpertProfileService legalExpertProfileService) {
        this.legalExpertProfileService = legalExpertProfileService;
    }

    @PostMapping
    public LegalExpertProfile create(@Valid @RequestBody LegalExpertProfile legalExpertProfile) {
        return this.legalExpertProfileService.create(legalExpertProfile);
    }

    @GetMapping(ID_ID)
    public LegalExpertProfile read(@PathVariable String id) {
        return this.legalExpertProfileService.read(id);
    }

    @PutMapping(ID_ID)
    public LegalExpertProfile update(@PathVariable String id, @RequestBody LegalExpertProfile legalExpertProfile) {
        return this.legalExpertProfileService.update(id, legalExpertProfile);
    }

    @DeleteMapping(ID_ID)
    public Map<String, String> delete(@PathVariable String id) {
        this.legalExpertProfileService.delete(id);

        return Map.of("message", "El perfil fue eliminado de manera exitosa");
    }

    @GetMapping
    public Stream<LegalExpertProfile> findAll() {
        return this.legalExpertProfileService.findAll();
    }

    @PatchMapping
    public void updatePartial(@RequestBody List<LegalExpertProfile> updates) {
        this.legalExpertProfileService.updatePartial(updates);
    }
}