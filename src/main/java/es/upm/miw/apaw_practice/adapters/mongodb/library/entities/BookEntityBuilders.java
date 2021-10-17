package es.upm.miw.apaw_practice.adapters.mongodb.library.entities;

import java.time.LocalDate;

public interface BookEntityBuilders {
    interface Id {
        Isbn id(String id);
    }

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
        CategoryEntity publicationDate(LocalDate publicationDate);
    }

    interface CategoryEntity {
        Optionals category(es.upm.miw.apaw_practice.adapters.mongodb.library.entities.CategoryEntity category);
    }

    interface Optionals {
        Optionals authors(es.upm.miw.apaw_practice.adapters.mongodb.library.entities.AuthorEntity author);

        BookEntity build();
    }
}
