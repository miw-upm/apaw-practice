package es.upm.miw.apaw_practice.adapters.mongodb.library;

import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.BookRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.LibraryRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.LoanRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.BookWriterRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.BookEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.LibraryEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.LoanEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.BookWriterEntity;
import es.upm.miw.apaw_practice.domain.models.library.BookWriter;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Service
public class LibrarySeederService {
    @Autowired
    private BookWriterRepository bookWriterRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private LoanRepository loanRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Library Initial Load -----------");

        BookWriterEntity[] bookWriters = {
                new BookWriterEntity(new BookWriter("Owen King", "Owen", 6)),
                new BookWriterEntity(new BookWriter("Stephen King", "Stephen", 20)),
                new BookWriterEntity(new BookWriter("Echo Chen", "San Mao", 23)),
                new BookWriterEntity(new BookWriter("Cixin Liu", "Cixin", 18)),
                new BookWriterEntity(new BookWriter("Autor","Menos tres libros",2)),
                new BookWriterEntity(new BookWriter("Autor","Autor Duplicado Para Test",2))
        };
        this.bookWriterRepository.saveAll(Arrays.asList(bookWriters));

        BookEntity[] books = {
                new BookEntity("Bellas durmientes", "9788401020414", LocalDate.of(2018, 2, 1), Arrays.asList(bookWriters[0], bookWriters[1])),
                new BookEntity("El bosque oscuro", "9788413146454", LocalDate.of(2023, 4, 13), List.of(bookWriters[3])),
                new BookEntity("Diarios de las canarias", "9788416738090", LocalDate.of(2017, 10, 4), List.of(bookWriters[2])),
                new BookEntity("El problema de los tres cuerpos", "9788466659734", LocalDate.of(2016, 9, 28), List.of(bookWriters[3])),
                new BookEntity("Libro con autor menos de tres libros", "9788888888888", LocalDate.of(2000, 10, 1), Arrays.asList(bookWriters[4], bookWriters[5]))
        };
        this.bookRepository.saveAll(Arrays.asList(books));

        LoanEntity[] loans = {
                new LoanEntity("BD123", books[0], LocalDateTime.now(), LocalDateTime.now().plusDays(7), true),
                new LoanEntity("EBO456", books[1], LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(3), false),
                new LoanEntity("DLC789", books[2], LocalDateTime.now().minusDays(4), LocalDateTime.now().plusDays(3), true),
                new LoanEntity("EPLTC111", books[3], LocalDateTime.now().minusDays(17), LocalDateTime.now().plusDays(10), false),
                new LoanEntity("LAMTL222",books[4],LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(6),true)
        };
        this.loanRepository.saveAll(Arrays.asList(loans));

        LibraryEntity[] libraries = {
                new LibraryEntity("Biblioteca Nacional", "Calle atocha 10", LocalDate.of(1996, 10, 1), Arrays.asList(books[0], books[2])),
                new LibraryEntity("Biblioteca universitaria", "Calle universidad 20", LocalDate.of(1980, 1, 9), Arrays.asList(books[1], books[2], books[3])),
                new LibraryEntity("Biblioteca territorial", "Calle sol 1", LocalDate.of(2000,2,2),List.of(books[4]))
        };
        this.libraryRepository.saveAll(Arrays.asList(libraries));

    }

    public void deleteAll() {
        this.loanRepository.deleteAll();
        this.bookRepository.deleteAll();
        this.bookWriterRepository.deleteAll();
        this.libraryRepository.deleteAll();
    }
}
