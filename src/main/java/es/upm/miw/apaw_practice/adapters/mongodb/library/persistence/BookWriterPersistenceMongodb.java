package es.upm.miw.apaw_practice.adapters.mongodb.library.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.BookRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.BookWriterRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.BookEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.BookWriterEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.library.BookWriter;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.BookWriterPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("bookWriterPersistence")
public class BookWriterPersistenceMongodb implements BookWriterPersistence {
    private final BookWriterRepository bookWriterRepository;
    private final BookRepository bookRepository;
    @Autowired
    public BookWriterPersistenceMongodb(BookWriterRepository bookWriterRepository, BookRepository bookRepository){
        this.bookWriterRepository = bookWriterRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookWriter readByNickname(String nickname){
        return this.bookWriterRepository
                .findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException("BookWriter nickname: " + nickname))
                .toBookWriter();
    }

    @Override
    public boolean existsNickname(String nickname){
        return this.bookWriterRepository
                .findByNickname(nickname)
                .isPresent();
    }

    @Override
    public BookWriter create(BookWriter bookWriter) {
        return this.bookWriterRepository
                .save(new BookWriterEntity(bookWriter))
                .toBookWriter();
    }

    @Override
    public BookWriter updateNumberOfBook(BookWriter bookWriter){
        BookWriterEntity bookWriterEntity = this.bookWriterRepository
                .findByNickname(bookWriter.getNickname())
                .orElseThrow(() -> new NotFoundException("BookWriter nickname: " + bookWriter.getNumberOfBook()));
        bookWriterEntity.setNumberOfBook(bookWriter.getNumberOfBook());
        return this.bookWriterRepository.save(bookWriterEntity).toBookWriter();
    }

    @Override
    public List<String> findNameOfBookWriterByBookIsbn(String isbn){
        Optional<BookEntity> bookEntity = this.bookRepository.findByIsbn(isbn);
        if(bookEntity.isPresent()){
            List<BookWriterEntity> bookWriterEntityList = bookEntity.get().getBookWriterEntities();
            return this.bookWriterRepository.findAll().stream()
                    .filter(bookWriterEntityList::contains)
                    .map(BookWriterEntity::getName)
                    .distinct()
                    .collect(Collectors.toList());
        }else{
            return null;
        }
    }

}
