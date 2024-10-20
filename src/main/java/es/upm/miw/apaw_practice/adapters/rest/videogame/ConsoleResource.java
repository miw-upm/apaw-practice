package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.videogame.Console;
import es.upm.miw.apaw_practice.domain.services.videogame.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(ConsoleResource.CONSOLES)
public class ConsoleResource {

    static final String CONSOLES = "/consoles";
    static final String SEARCH = "/search";
    static final String CONSOLE_REFERENCE = "/{consoleReference}";

    private final ConsoleService consoleService;

    @Autowired
    public ConsoleResource(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @GetMapping(SEARCH)
    public Stream<Console> findByConsoleReference(@RequestParam String console) {
        String consoleReference = new LexicalAnalyzer().extractWithAssure(console, "consoleReference");
        return this.consoleService.findByConsoleReference(consoleReference);
    }

    @DeleteMapping(CONSOLE_REFERENCE)
    public void delete(@PathVariable String consoleReference) {
        this.consoleService.delete(consoleReference);
    }

    @PostMapping
    public Console create(@RequestBody Console console) {
        return consoleService.create(console);
    }
}