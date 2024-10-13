package es.upm.miw.apaw_practice.adapters.mongodb.movies.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Document
public class StudioEntity {

    public static final String NEWLINE_WITH_COMMA = ",\n";

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private LocalDate foundedDate;
    private BigDecimal marketCapitalization;
    @DBRef
    private Set<MovieEntity> producedMovies;

    public StudioEntity(){
    //empty for framework
    }

    public StudioEntity(String name, LocalDate foundedDate, BigDecimal marketCapitalization, Set<MovieEntity> producedMovies) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.foundedDate = foundedDate;
        this.marketCapitalization = marketCapitalization;
        this.producedMovies = producedMovies;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public LocalDate getFoundedDate() { return foundedDate; }

    public void setFoundedDate(LocalDate foundedDate) { this.foundedDate = foundedDate; }

    public BigDecimal getMarketCapitalization() { return marketCapitalization; }

    public void setMarketCapitalization(BigDecimal marketCapitalization) { this.marketCapitalization = marketCapitalization; }

    public Set<MovieEntity> getProducedMovies() { return producedMovies; }

    public void setProducedMovies(Set<MovieEntity> producedMovies) { this.producedMovies = producedMovies; }

    @Override
    public String toString() {
        return "StudioEntity {\n" +
                "  name: \"" + name + NEWLINE_WITH_COMMA +
                "  foundedDate: \"" + foundedDate + NEWLINE_WITH_COMMA +
                "  marketCapitalization: " + marketCapitalization + NEWLINE_WITH_COMMA +
                "  producedMovies: " + producedMovies + "\n" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudioEntity studio = (StudioEntity) o;
        return name.equals(studio.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
