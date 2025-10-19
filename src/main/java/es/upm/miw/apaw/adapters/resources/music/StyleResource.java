package es.upm.miw.apaw.adapters.resources.music;

import es.upm.miw.apaw.domain.models.music.Style;
import es.upm.miw.apaw.domain.services.music.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StyleResource.STYLES)
public class StyleResource {

    public static final String STYLES = "/music/styles";
    public static final String STYLE_ID = "/{genre}";

    private final StyleService styleService;

    @Autowired
    public StyleResource(StyleService styleService) {
        this.styleService = styleService;
    }

    @PatchMapping(STYLE_ID)
    public void patch(@PathVariable String genre, @RequestBody Style style) {
        this.styleService.patch(genre, style);
    }
}
