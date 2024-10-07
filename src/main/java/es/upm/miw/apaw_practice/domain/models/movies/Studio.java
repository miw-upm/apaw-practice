package es.upm.miw.apaw_practice.domain.models.movies;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Studio {

    public static final String NEWLINE_WITH_COMMA = ",\n";

    private String name;
    private LocalDate foundedDate;
    private BigDecimal marketCapitalization;
    private Set<Movie> producedMovies;

    public Studio() {
        //empty for framework
    }

    public Studio(String name, LocalDate foundedDate, BigDecimal marketCapitalization, Set<Movie> producedMovies) {
        this.name = name;
        this.foundedDate = foundedDate;
        this.marketCapitalization = marketCapitalization;
        this.producedMovies = new HashSet<>(producedMovies);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(LocalDate foundedDate) {
        this.foundedDate = foundedDate;
    }

    public BigDecimal getMarketCapitalization() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(BigDecimal marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    public Set<Movie> getMovies() {
        return new HashSet<>(producedMovies);
    }

    public Movie getMovie(String imdbId) {
        for (Movie movie : producedMovies) {
            if (movie.getImdbId().equals(imdbId)) {
                return movie;
            }
        }
        return null;
    }

    public void addMovie(Movie movie) {
        producedMovies.add(movie);
    }

    @Override
    public String toString() {
        return "Studio {\n" +
                "  name: \"" + name + NEWLINE_WITH_COMMA +
                "  foundedDate: \"" + foundedDate + NEWLINE_WITH_COMMA +
                "  marketCapitalization: " + marketCapitalization + NEWLINE_WITH_COMMA +
                "  producedMovies: " + producedMovies + "\n" +
                "}";
    }
}
