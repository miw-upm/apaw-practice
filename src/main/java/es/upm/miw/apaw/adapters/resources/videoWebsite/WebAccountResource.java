package es.upm.miw.apaw.adapters.resources.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import es.upm.miw.apaw.domain.services.videoWebsite.WebAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(WebAccountResource.WEBACCOUNT)
public class WebAccountResource {

    public static final String WEBACCOUNT = "/videoWebsite/webAccount";
    public static final String MOBILE = "/{mobile}";
    public static final String TOTAL_VIEWS = "/total-Views";

    private final WebAccountService webAccountService;

    @Autowired
    public WebAccountResource(WebAccountService webAccountService) {
        this.webAccountService = webAccountService;
    }

    @GetMapping("/{id}")
    public WebAccount findById(@PathVariable UUID id) {
        return this.webAccountService.findById(id);
    }

    @GetMapping(MOBILE + TOTAL_VIEWS)
    public Integer obtainTotalViewsByMobile(@PathVariable("mobile") String mobile) {
        return this.webAccountService.obtainTotalViewsByMobile(mobile);
    }
}
