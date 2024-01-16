package es.upm.miw.apaw_practice.domain.services.library;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.library.LibrarySeederService;
import es.upm.miw.apaw_practice.domain.models.library.BookWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
public class BookWriterServiceIT {
    @Autowired
    private BookWriterService bookWriterService;
    @Autowired
    private LibrarySeederService librarySeederService;

    @AfterEach
    void resetDataBase() {
        this.librarySeederService.deleteAll();
        this.librarySeederService.seedDatabase();
    }

    @BeforeEach
    void resetDataBaseBefore() {

        this.librarySeederService.deleteAll();
        this.librarySeederService.seedDatabase();
    }

    @Test
    void testCreateBookWriter() {
        BookWriter bookWriter = this.bookWriterService.create(new BookWriter("Mo Yan", "M. Yan", 50));
        assertNotNull(bookWriter);
        assertEquals("Mo Yan", bookWriter.getName());
        assertEquals("M. Yan", bookWriter.getNickname());
        assertEquals(50, bookWriter.getNumberOfBook());
    }

    @Test
    void testUpdateNumberOfBook() {
        BookWriter bookWriter = this.bookWriterService.updateNumberOfBook("Cixin", 30);
        assertEquals("Cixin Liu", bookWriter.getName());
        assertEquals("Cixin", bookWriter.getNickname());
        assertEquals(30, bookWriter.getNumberOfBook());
        this.bookWriterService.updateNumberOfBook("Cixin", 18);
    }

    @Test
    void testFindAverageOfNumberOfBookByLibraryName() {
        BigDecimal average = this.bookWriterService.findAverageOfNumberOfBookByLibraryName("Biblioteca territorial");
        assertEquals(BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_UP), average.setScale(2, RoundingMode.HALF_UP));
        BigDecimal average2 = this.bookWriterService.findAverageOfNumberOfBookByLibraryName("Biblioteca universitaria");
        assertEquals(BigDecimal.valueOf(19.67).setScale(2, RoundingMode.HALF_UP), average2.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void testFindNamesOfBookWritersByIsbn(){
        List<String> bookWritersName = this.bookWriterService.findNamesOfBookWriterByIsbn("9788888888888");
        assertNotNull(bookWritersName);
        assertEquals(1, bookWritersName.size());
        assertEquals("Autor", bookWritersName.get(0));
    }
}
