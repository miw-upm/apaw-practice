package es.upm.miw.apaw_practice.adapters.rest.theme_park;

import es.upm.miw.apaw_practice.domain.models.theme_park.ThemePark;
import es.upm.miw.apaw_practice.domain.services.theme_park.ThemeParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ThemeParkResource.THEME_PARKS)
public class ThemeParkResource {
    public static final String THEME_PARKS = "/theme-parks";
    static final String ID_ID = "/{id}";
    private final ThemeParkService themeParkService;

    @Autowired
    public ThemeParkResource(ThemeParkService themeParkService) {
        this.themeParkService = themeParkService;
    }

    @PatchMapping(ID_ID)
    public ThemePark updateParkStatus(@PathVariable String id) {
        return this.themeParkService.updateParkStatus(id);
    }


}
