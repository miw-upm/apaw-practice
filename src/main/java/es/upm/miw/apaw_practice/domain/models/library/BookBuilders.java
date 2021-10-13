package es.upm.miw.apaw_practice.domain.models.library;

import java.time.LocalDate;

public interface BookBuilders {
    interface Isbn {
        Title isbn(String isbn);
    }

    interface Title {
        Available title(String title);
    }

    interface Available {
        NumbersOfPages available(Boolean available);
    }

    interface NumbersOfPages {
        PublicationDate numbersOfPages(Integer numbersOfPages);
    }

    interface PublicationDate {
        Category publicationDate(LocalDate publicationDate);
    }

    interface Category {
        Author category(es.upm.miw.apaw_practice.domain.models.library.Category category);
    }

    interface Author {
        Optionals authors(es.upm.miw.apaw_practice.domain.models.library.Author author);

    }

    interface Optionals{
        Book build();
    }

}
