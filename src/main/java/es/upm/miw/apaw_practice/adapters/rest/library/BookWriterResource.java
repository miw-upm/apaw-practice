package es.upm.miw.apaw_practice.adapters.rest.library;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.adapters.rest.library.dto.BookWriterCollectionDto;
import es.upm.miw.apaw_practice.domain.models.library.BookWriter;
import es.upm.miw.apaw_practice.domain.services.library.BookWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(BookWriterResource.BOOKWRITER)
public class BookWriterResource {
    static final String BOOKWRITER = "/library/bookWriters";
    static final String NICKNAME_ID = "/{nickname}";
    static final String NUMBER_OF_BOOK = "/number-of-book";
    static final String SEARCH = "/search-by-name";
    private final BookWriterService bookWriterService;

    @Autowired
    public BookWriterResource(BookWriterService bookWriterService) {
        this.bookWriterService = bookWriterService;
    }

    @PostMapping
    public BookWriter create(@RequestBody BookWriter bookWriter) {
        return this.bookWriterService.create(bookWriter);
    }

    @PutMapping(NICKNAME_ID + NUMBER_OF_BOOK)
    public BookWriter updateBookWriterNumberOfBook(@PathVariable String nickname, @RequestBody Integer numberOfBook) {
        return this.bookWriterService.updateNumberOfBook(nickname, numberOfBook);
    }

    @GetMapping(SEARCH)
    public BigDecimal findAverageOfNumberOfBookByLibraryName(@RequestParam String q){
        String name = new LexicalAnalyzer().extractWithAssure(q, "name");
        return this.bookWriterService.findAverageOfNumberOfBookByLibraryName(name);
    }


    @GetMapping(SEARCH)
    public BookWriterCollectionDto findNamesOfBookWriterByIsbn(@RequestParam String q){
        String isbn = new LexicalAnalyzer().extractWithAssure(q,"isbn");
        return this.bookWriterService.findNamesOfBookWriterByIsbn(isbn);
    }
}
