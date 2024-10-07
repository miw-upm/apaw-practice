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

    public Award() {
        //empty for framework
    }

    public Award(String nameCategoryAndYear, String name, String category, LocalDate year, Set<Movie> awardedMovies, Set<Actor> awardedActors) {
        this.nameCategoryAndYear = nameCategoryAndYear;
        this.name = name;
        this.category = category;
        this.year = year;
    }

    public String getNameCategoryAndYear() { return nameCategoryAndYear; }

    public void setNameCategoryAndYear(String nameCategoryAndYear) { this.nameCategoryAndYear = nameCategoryAndYear; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public LocalDate getYear() { return year; }

    public void setYear(LocalDate year) { this.year = year; }

    @Override
    public String toString() {
        return "Award {\n" +
                "  nameCategoryAndYear: \"" + nameCategoryAndYear + NEWLINE_WITH_COMMA +
                "  name: \"" + name + NEWLINE_WITH_COMMA +
                "  category: \"" + category + NEWLINE_WITH_COMMA +
                "  year: " + year +  "\n" +
                "}";
    }

}
