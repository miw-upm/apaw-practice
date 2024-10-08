package es.upm.miw.apaw_practice.adapters.rest.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import es.upm.miw.apaw_practice.domain.services.wushu_sport.CompetitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CompetitorResource.COMPETITOR)
public class CompetitorResource {
    static final String COMPETITOR = "/wushu/competitor";

    static final String LICENCE_ID = "/{licence}";

    private final CompetitorService competitorService;

    @Autowired
    public CompetitorResource(CompetitorService competitorService){
        this.competitorService = competitorService;
    }

    @GetMapping(LICENCE_ID)
    public Competitor getCompetitor(@PathVariable String licence){
        return this.competitorService.readByLicence(licence);
    }
}
