package es.upm.miw.apaw_practice.adapters.mongodb.movies.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Document
public class AwardEntity {

    public static final String NEWLINE_WITH_COMMA = ",\n";

    @Id
    private String id;
    @Indexed(unique = true)
    private String nameCategoryAndYear;
    private String name;
    private String category;
    private LocalDate year;

    public AwardEntity() {
        //empty for framework
    }

    public AwardEntity(String nameCategoryAndYear, String name, String category, LocalDate year){
        this.id = UUID.randomUUID().toString();
        this.nameCategoryAndYear = nameCategoryAndYear;
        this.name = name;
        this.category = category;
        this.year = year;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
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
        return "AwardEntity {\n" +
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
        AwardEntity award = (AwardEntity) o;
        return nameCategoryAndYear.equals(award.nameCategoryAndYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameCategoryAndYear);
    }

}
