package es.upm.miw.apaw_practice.adapters.rest.theme_park;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.theme_park.ThemePark;
import es.upm.miw.apaw_practice.domain.models.theme_park.ThemeParkOpenedUpdating;
import es.upm.miw.apaw_practice.domain.services.theme_park.ThemeParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(ThemeParkResource.THEME_PARKS)
public class ThemeParkResource {
    public static final String THEME_PARKS = "/theme-parks";
    static final String RIDES = "/rides";
    static final String ID_ID = "/{id}";
    static final String SEARCH = "/search";

    private final ThemeParkService themeParkService;

    @Autowired
    public ThemeParkResource(ThemeParkService themeParkService) {
        this.themeParkService = themeParkService;
    }

    @PatchMapping(ID_ID)
    public ThemePark updateParkStatus(@PathVariable String id) {
        return this.themeParkService.updateParkStatus(id);
    }

    @PatchMapping
    public void updateAllParkStatus(@RequestBody List<ThemeParkOpenedUpdating> themeParkOpenedUpdatingList) {
        this.themeParkService.updateAllParkStatus(themeParkOpenedUpdatingList.stream());
    }

    @GetMapping(SEARCH)
    public BigDecimal getSumPriceByNick(@RequestParam String q){
        String nick = new LexicalAnalyzer().extractWithAssure(q, "nick").trim();
        return this.themeParkService.getSumPriceByNick(nick);
    }

    @GetMapping(RIDES + SEARCH)
    public List<String> getIdsByAfterEntranceDate(@RequestParam String q){
        String date = new LexicalAnalyzer().extractWithAssure(q, "entranceDate").trim();
        LocalDateTime entranceDate = LocalDateTime.parse(date.replace(".", ":" ), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return this.themeParkService.getIdsByAfterEntranceDate(entranceDate);
    }

}
