package es.upm.miw.apaw_practice.domain.models.movies;

import java.time.LocalDate;
import java.util.Objects;

public class Award {

    public static final String NEWLINE_WITH_COMMA = ",\n";

    private String nameCategoryAndYear;
    private String name;
    private String category;
    private LocalDate year;

    public static AwardBuilders.NameCategoryAndYear builder() { return new Builder(); }

    public Award() {
        //empty for framework
    }

    public Award(String nameCategoryAndYear, String name, String category, LocalDate year) {
        this.nameCategoryAndYear = nameCategoryAndYear;
        this.name = name;
        this.category = category;
        this.year = year;
    }

    public String getNameCategoryYear() { return nameCategoryAndYear; }

    public void setNameCategoryYear(String nameCategoryAndYear) { this.nameCategoryAndYear = nameCategoryAndYear; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Award award = (Award) o;
        return nameCategoryAndYear.equals(award.nameCategoryAndYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameCategoryAndYear);
    }

    public static class Builder implements
            AwardBuilders.NameCategoryAndYear,
            AwardBuilders.Name,
            AwardBuilders.Category,
            AwardBuilders.Year,
            AwardBuilders.Builder {

        private String nameCategoryAndYear;
        private String name;
        private String category;
        private LocalDate year;

        private Builder() {
            //private constructor
        }

        @Override
        public AwardBuilders.Name nameCategoryAndYear(String nameCategoryAndYear) {
            this.nameCategoryAndYear = nameCategoryAndYear;
            return this;
        }

        @Override
        public AwardBuilders.Category name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public AwardBuilders.Year category(String category) {
            this.category = category;
            return this;
        }

        @Override
        public AwardBuilders.Builder year(LocalDate year) {
            this.year = year;
            return this;
        }

        @Override
        public Award build() {
            return new Award(nameCategoryAndYear, name, category, year);
        }
    }
}
