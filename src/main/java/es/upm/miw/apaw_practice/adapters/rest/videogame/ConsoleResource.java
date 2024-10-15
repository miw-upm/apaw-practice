package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.videogame.Console;
import es.upm.miw.apaw_practice.domain.services.videogame.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(ConsoleResource.CONSOLES)
public class ConsoleResource {

    static final String CONSOLES = "/videogame/consoles";
    static final String SEARCH = "/search/";

    private final ConsoleService consoleService;

    @Autowired
    public ConsoleResource(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }
    @GetMapping(SEARCH)
    public Stream<Console> findByConsoleReference(@RequestParam String consoleReference) {
        String reference = new LexicalAnalyzer().extractWithAssure(consoleReference, "reference");
        return this.consoleService.findByConsoleReference(reference);
    }
}
