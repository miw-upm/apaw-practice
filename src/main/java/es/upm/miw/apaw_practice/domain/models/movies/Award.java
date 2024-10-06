package es.upm.miw.apaw_practice.domain.models.movies;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Award {

    public static final String NEWLINE_WITH_COMMA = ",\n";

    private String nameCategoryAndYear;
    private String name;
    private String category;
    private LocalDate year;
    private Set<Movie> awardedMovies;
    private Set<Actor> awardedActors;

    public Award() {
        //empty for framework
    }

    public Award(String nameCategoryAndYear, String name, String category, LocalDate year, Set<Movie> awardedMovies, Set<Actor> awardedActors) {
        this.nameCategoryAndYear = nameCategoryAndYear;
        this.name = name;
        this.category = category;
        this.year = year;
        this.awardedMovies = new HashSet<>(awardedMovies);
        this.awardedActors = new HashSet<>(awardedActors);
    }

    public String getNameCategoryAndYear() { return nameCategoryAndYear; }

    public void setNameCategoryAndYear(String nameCategoryAndYear) { this.nameCategoryAndYear = nameCategoryAndYear; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public LocalDate getYear() { return year; }

    public void setYear(LocalDate year) { this.year = year; }

    public Set<Movie> getAwardedMovies() { return awardedMovies; }

    public void setAwardedMovies(Set<Movie> awardedMovies) { this.awardedMovies = awardedMovies; }

    public void addAwardedMovie(Movie movie) { awardedMovies.add(movie); }

    public void removeAwardedMovie(Movie movie) { awardedMovies.remove(movie); }

    public Set<Actor> getAwardedActors() { return awardedActors; }

    public void setAwardedActors(Set<Actor> awardedActors) { this.awardedActors = awardedActors; }

    public void addAwardedActor(Actor actor) { awardedActors.add(actor); }

    public void removeAwardedActor(Actor actor) { awardedActors.remove(actor); }

    @Override
    public String toString() {
        return "Award {\n" +
                "  nameCategoryAndYear: \"" + nameCategoryAndYear + NEWLINE_WITH_COMMA +
                "  name: \"" + name + NEWLINE_WITH_COMMA +
                "  category: \"" + category + NEWLINE_WITH_COMMA +
                "  year: " + year + NEWLINE_WITH_COMMA +
                "  awardedMovies: " + awardedMovies + NEWLINE_WITH_COMMA +
                "  awardedActors: " + awardedActors + "\n" +
                "}";
    }

}
