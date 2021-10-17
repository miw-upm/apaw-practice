package es.upm.miw.apaw_practice.adapters.rest.library;

import es.upm.miw.apaw_practice.domain.models.library.Reader;
import es.upm.miw.apaw_practice.domain.services.library.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ReaderResource.READERS)
public class ReaderResource {
    static final String READERS = "/library/readers";
    static final String EMAIL_ID = "/{email}";

    private final ReaderService readerService;

    @Autowired
    public ReaderResource(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping
    public Reader create(@RequestBody Reader reader) {
        return this.readerService.create(reader);
    }

    @DeleteMapping(EMAIL_ID)
    public void delete(@PathVariable String email) {
        this.readerService.delete(email);
    }
}
