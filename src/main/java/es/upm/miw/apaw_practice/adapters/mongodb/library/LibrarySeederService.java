package es.upm.miw.apaw_practice.adapters.mongodb.library;

import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.AuthorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.BookRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.CategoryRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.ReaderRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.AuthorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.BookEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.CategoryEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.ReaderEntity;
import es.upm.miw.apaw_practice.domain.models.library.Gender;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@Service
public class LibrarySeederService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ReaderRepository readerRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Library Initial Load -----------");
        CategoryEntity[] categories = {
                new CategoryEntity("Horror", "causes discomfort and fear"),
                new CategoryEntity("Drama", "a conflict that takes place in the lives of character"),
                new CategoryEntity("Poetry", "authors choose a particular rhythm and style to evoke and portray various emotions and ideas"),
                new CategoryEntity("Thriller", "the hero attempt to stop and defeat the villain to save their own life rather than uncover a specific crime"),
                new CategoryEntity("Romance", "makes your heart all warm and fuzzy focuses on the love story of the main protagonists"),
                new CategoryEntity("Society", "makes your heart all warm and fuzzy focuses on the love story of the main protagonists")
        };
        this.categoryRepository.saveAll(Arrays.asList(categories));

        AuthorEntity[] authors = {
                new AuthorEntity("Alda do Espírito Santo", "Santomean", "Poetry"),
                new AuthorEntity("José Saramago", "Portuguese", "Romance"),
                new AuthorEntity("Juan Gómez-Jurado", "Spanish", "Thriller"),
                new AuthorEntity("Manuel Vilas", "Spanish", "Poetry"),
                new AuthorEntity("Francisco Javier Olmedo", "Spanish", "Horror"),
                new AuthorEntity("William Shakespeare", "English", "Drama"),
                new AuthorEntity("Machado de Assis", "Brazilian", "Poetry"),
                new AuthorEntity("Zygmunt Bauman", "", ""),
                new AuthorEntity("Leonidas Donskis", "", "")
        };
        this.authorRepository.saveAll(Arrays.asList(authors));

        BookEntity[] books = {
                BookEntity.builder().id(UUID.randomUUID().toString()).isbn("1A").title("Mataram o rio da minha cidade").available(true).numbersOfPages(77).publicationDate(LocalDate.of(2002, 3, 12)).category(categories[2]).authors(authors[0]).build(),
                BookEntity.builder().id(UUID.randomUUID().toString()).isbn("1B").title("Ensaio sobre a Cegueira").available(true).numbersOfPages(344).publicationDate(LocalDate.of(1995, 1, 1)).category(categories[4]).authors(authors[1]).build(),
                BookEntity.builder().id(UUID.randomUUID().toString()).isbn("1C").title("Reina roja").available(true).numbersOfPages(568).publicationDate(LocalDate.of(2018, 11, 8)).category(categories[3]).authors(authors[2]).build(),
                BookEntity.builder().id(UUID.randomUUID().toString()).isbn("1D").title("Alegría").available(true).numbersOfPages(351).publicationDate(LocalDate.of(2019, 11, 5)).category(categories[3]).authors(authors[3]).build(),
                BookEntity.builder().id(UUID.randomUUID().toString()).isbn("1E").title("Mal Nacido").available(true).numbersOfPages(416).publicationDate(LocalDate.of(2019, 7, 11)).category(categories[0]).authors(authors[4]).build(),
                BookEntity.builder().id(UUID.randomUUID().toString()).isbn("1F").title("The Tragedy of Romeo and Juliet").available(true).numbersOfPages(179).publicationDate(LocalDate.of(1597, 10, 9)).category(categories[1]).authors(authors[5]).build(),
                BookEntity.builder().id(UUID.randomUUID().toString()).isbn("1G").title("O Alienista ").available(true).numbersOfPages(66).publicationDate(LocalDate.of(2021, 10, 11)).category(categories[3]).authors(authors[6]).build(),
                BookEntity.builder().id(UUID.randomUUID().toString()).isbn("1H").title("Maldad líquida").available(true).numbersOfPages(348).publicationDate(LocalDate.of(2019, 1, 22)).category(categories[5]).authors(authors[7]).authors(authors[8]).build()

        };
        this.bookRepository.saveAll(Arrays.asList(books));

        ReaderEntity[] readers = {
                new ReaderEntity("Alexander", Gender.M, "al@xpto.com"),
                new ReaderEntity("Manuela", Gender.F, "ma@xpto.com"),
                new ReaderEntity("Joana", Gender.F, "jo@xpto.com"),
                new ReaderEntity("Cristiano", Gender.M, "cr@xpto.com"),
                new ReaderEntity("Ronaldinho", Gender.M, "ro@xpto.com"),
                new ReaderEntity("Zidane", Gender.M, "zi@xpto.com"),
                new ReaderEntity("Serena", Gender.F, "se@xpto.com"),
                new ReaderEntity("Jenifer", Gender.F, "jlo@xpto.com")
        };
        this.readerRepository.saveAll(Arrays.asList(readers));
    }
}
