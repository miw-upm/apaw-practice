package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

public interface GameBuilders {
    interface GameName {
        NumPlayers gameName(String gameName);
    }

    interface NumPlayers {
        Genre numPlayers(Integer numPlayers);
    }

    interface Genre {
        NumberOfCopies genre(String genre);
    }

    interface NumberOfCopies {
        Builder numberOfCopies(Integer numberOfCopies);
    }

    interface Builder {
        Game build();
    }
}
