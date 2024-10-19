package es.upm.miw.apaw_practice.adapters.rest.basketball;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketBall;
import es.upm.miw.apaw_practice.domain.services.basketball.BasketBallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BasketBallResource.BALLS)
public class BasketBallResource {

    static final String ID_ID = "/{id}";
    static final String SEARCH = "/search";
    static final String BALLS = "/basket/balls";
    static final String DISTINCT_BRANDS = "/distinct-brands";

    private final BasketBallService basketBallService;

    @Autowired
    public BasketBallResource(BasketBallService basketBallService) {
        this.basketBallService = basketBallService;
    }

    @PutMapping(ID_ID)
    public BasketBall update(@PathVariable Integer id, @RequestBody BasketBall basketBall) {
        return this.basketBallService.update(id,basketBall);
    }

    @GetMapping(SEARCH + DISTINCT_BRANDS)
    public List<String> getDistinctBrands(@RequestParam String q) {
        String league = new LexicalAnalyzer().extractWithAssure(q, "league").trim();
        String playerName = new LexicalAnalyzer().extractWithAssure(q, "playerName").trim();
        return this.basketBallService.getDistinctBrands(league,playerName);
    }
}
