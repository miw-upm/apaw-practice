package es.upm.miw.apaw_practice.domain.models.wuhshu_sport;


public interface CompetitionFormBuilders {

    interface Score {
        Duration score(double score);
    }

    interface Duration{
        Category duration(java.time.Duration duration);
    }

    interface Category{
        Optionals category(String category);
    }

    interface Optionals{
        CompetitionForm  build();
    }

}
