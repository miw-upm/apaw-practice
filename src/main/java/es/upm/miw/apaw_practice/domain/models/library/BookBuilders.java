package es.upm.miw.apaw_practice.domain.models.library;

import java.time.LocalDate;

public interface BookBuilders {

    interface Isbn{
        Title isbn(String isbn);
    }

    interface Title{
        Available title(String title);
    }

    interface Available{
        NumbersOfPages available(Boolean available);
    }

    interface NumbersOfPages{
        PublicationDate numbersOfPages(Integer numbersOfPages);
    }

    interface PublicationDate{
        ICategory publicationDate(LocalDate publicationDate);
    }

    interface ICategory{
        IAuthor category(Category category);
    }

    interface IAuthor{
        Optionals authors(Author author);
    }

    interface Optionals{
        Book build();
    }
}
