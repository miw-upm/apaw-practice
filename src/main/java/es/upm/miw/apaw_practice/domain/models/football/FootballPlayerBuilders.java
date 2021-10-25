package es.upm.miw.apaw_practice.domain.models.football;

public interface FootballPlayerBuilders {

    interface Defense {
        GoalsScored defense(Boolean defense);
    }

    interface GoalsScored {
        Age goalsScored(Integer goalsScored);
    }

    interface Age {
        Name age(Integer age);
    }

    interface Name {
        FootballPlayerBuild name(String name);
    }

    interface FootballPlayerBuild {
        FootballPlayer build();
    }
}
