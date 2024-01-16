package es.upm.miw.apaw_practice.domain.services.library;

import es.upm.miw.apaw_practice.adapters.rest.library.dto.BookWriterCollectionDto;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.library.Book;
import es.upm.miw.apaw_practice.domain.models.library.BookWriter;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.BookPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.BookWriterPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.LibraryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookWriterService {
    private final BookWriterPersistence bookWriterPersistence;
    private final LibraryPersistence libraryPersistence;
    private final BookPersistence bookPersistence;

    @Autowired
    public BookWriterService(BookWriterPersistence bookWriterPersistence, LibraryPersistence libraryPersistence, BookPersistence bookPersistence){
        this.bookWriterPersistence = bookWriterPersistence;
        this.libraryPersistence = libraryPersistence;
        this.bookPersistence = bookPersistence;
    }

    public BookWriter create(BookWriter bookWriter){
        this.assertNicknameNotExist(bookWriter.getNickname());
        return this.bookWriterPersistence.create(bookWriter);
    }

    public BookWriter updateNumberOfBook(String nickname, Integer numberOfBook){
        BookWriter bookWriter = this.bookWriterPersistence.readByNickname(nickname);
        bookWriter.setNumberOfBook(numberOfBook);
        return this.bookWriterPersistence.updateNumberOfBook(bookWriter);
    }

    public void assertNicknameNotExist(String nickname){
        if(this.bookWriterPersistence.existsNickname(nickname)){
            throw new ConflictException("Nickname exist: "+nickname);
        }
    }

    public BigDecimal findAverageOfNumberOfBookByLibraryName(String name) {
       List<Book> bookList=this.libraryPersistence.findBookByLibraryName(name);
       if(bookList.isEmpty()){
           throw new NotFoundException("Library was not founded with name: "+name);
       }
       List<BookWriter> bookWriterList = bookList
               .stream()
               .flatMap(book -> book.getBookWriters().stream())
               .distinct()
               .toList();
       return BigDecimal.valueOf(bookWriterList.stream()
               .mapToDouble(BookWriter::getNumberOfBook)
               .average()
               .orElse(0.0));
    }

    public List<String> findNamesOfBookWriterByIsbn(String isbn) {
        return this.bookWriterPersistence.findNameOfBookWriterByBookIsbn(isbn);
    }
}
