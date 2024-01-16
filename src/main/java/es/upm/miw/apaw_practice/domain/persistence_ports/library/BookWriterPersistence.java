package es.upm.miw.apaw_practice.domain.persistence_ports.library;

import es.upm.miw.apaw_practice.domain.models.library.BookWriter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookWriterPersistence {
    BookWriter readByNickname(String nickname);
    boolean existsNickname(String nickname);
    BookWriter create(BookWriter bookWriter);
    BookWriter updateNumberOfBook(BookWriter bookWriter);
    List<String> findNameOfBookWriterByBookIsbn(String isbn);
}
