package es.upm.miw.apaw_practice.domain.models.movies;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Studio {
    private String id;
    private String name;
    private LocalDate foundedOn;
    private BigDecimal marketCapitalization;

    public Studio() {
        //empty for framework
    }

    public Studio(String id, String name, LocalDate foundedOn, BigDecimal marketCapitalization) {
        this.id = id;
        this.name = name;
        this.foundedOn = foundedOn;
        this.marketCapitalization = marketCapitalization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFoundedOn() {
        return foundedOn;
    }

    public void setFoundedOn(LocalDate foundedOn) {
        this.foundedOn = foundedOn;
    }

    public BigDecimal getMarketCapitalization() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(BigDecimal marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    @Override
    public String toString() {
        return "Studio {\n" +
                "  id: " + id + '\n' +
                "  name: \"" + name + "\",\n"  +
                "  foundedOn: \"" + foundedOn + "\",\n" +
                "  marketCapitalization: " + marketCapitalization + '\n' +
                "}";
    }
}
