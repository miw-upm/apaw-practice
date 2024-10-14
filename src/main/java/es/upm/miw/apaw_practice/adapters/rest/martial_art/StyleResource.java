package es.upm.miw.apaw_practice.adapters.rest.martial_art;
import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import es.upm.miw.apaw_practice.domain.services.martial_art.InstructorService;
import es.upm.miw.apaw_practice.domain.services.martial_art.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StyleResource.STYLE)

public class StyleResource {
    static final String STYLE = "/martial_art/style";
    static final String NAME = "/{name}";
    private final StyleService styleService;

    @Autowired
    public StyleResource(StyleService styleService) {
        this.styleService = styleService;
    }

    @PostMapping
    public Style create(@RequestBody Style style) {
        return this.styleService.create(style);
    }

    @GetMapping(NAME)
    public Style read(@PathVariable String name) {
        return this.styleService.read(name);
    }

    @DeleteMapping(NAME)
    public void delete(@PathVariable String name) {
        this.styleService.delete(name);
    }

    @PutMapping(NAME)
    public Style update(@PathVariable String name, @RequestBody Style style) {
        return this.styleService.update(name, style);
    }
}
