package es.upm.miw.apaw_practice.adapters.mongodb.library.entities;

import java.time.LocalDate;

public interface BookEntityBuilders {
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
        ICategory publicationDate(LocalDate publicationDate);
    }

    interface ICategory {
        IAuthor category(CategoryEntity category);
    }

    interface IAuthor {
        Optionals authors(AuthorEntity author);
    }

    interface Optionals {
        Optionals authors(AuthorEntity author);

        BookEntity build();
    }
}
