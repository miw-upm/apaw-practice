package es.upm.miw.apaw_practice.domain.persistence_ports.library;

import es.upm.miw.apaw_practice.domain.models.library.BookWriter;
import org.springframework.stereotype.Repository;

@Repository
public interface BookWriterPersistence {
    BookWriter readByNickname(String nickname);
    boolean existsNickname(String nickname);
    BookWriter create(BookWriter bookWriter);
    BookWriter updateNumberOfBook(BookWriter bookWriter);
}
