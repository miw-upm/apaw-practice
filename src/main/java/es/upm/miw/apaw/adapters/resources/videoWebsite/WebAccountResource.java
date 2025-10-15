package es.upm.miw.apaw.adapters.resources.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import es.upm.miw.apaw.domain.services.videoWebsite.WebAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(WebAccountResource.WEBACCOUNT)
public class WebAccountResource {

    public static final String WEBACCOUNT = "/videoWebsite/webAccount/";

    private final WebAccountService webAccountService;

    @Autowired
    public WebAccountResource(WebAccountService webAccountService) {
        this.webAccountService = webAccountService;
    }

    @GetMapping("/{id}")
    public WebAccount findById(@PathVariable UUID id) {
        return this.webAccountService.findById(id);
    }
}
