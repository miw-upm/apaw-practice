package es.upm.miw.apaw_practice.adapters.rest.shopping_center;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Provider;
import es.upm.miw.apaw_practice.domain.services.shopping_center.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProviderResource.PROVIDERS)
public class ProviderResource {
    static final String PROVIDERS = "/shopping_center/providers";

    private final ProviderService providerService;

    @Autowired
    public ProviderResource(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    public Provider create(@RequestBody Provider provider) {
        return this.providerService.create(provider);
    }
}
