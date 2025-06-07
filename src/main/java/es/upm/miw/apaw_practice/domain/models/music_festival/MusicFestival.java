package es.upm.miw.apaw_practice.domain.models.music_festival;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MusicFestival {

    private String name;
    private LocalDateTime creationDate;
    private BigDecimal budget;

    private List<Concert> concerts;

    public MusicFestival() {
        this.concerts = new ArrayList<>();
    }

    public MusicFestival(String name, LocalDateTime creationDate, BigDecimal budget) {
        this.name = name;
        this.creationDate = creationDate;
        this.budget = budget;
        this.concerts = new ArrayList<>();
    }

    public static MusicFestival ofName(MusicFestival musicFestival) {
        MusicFestival musicFestivalDto = new MusicFestival();
        musicFestivalDto.setName(musicFestival.getName());
        return musicFestivalDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public List<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }

    @Override
    public final boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (name.equals(((MusicFestival) obj).name));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public String toString() {
        return "MusicFestival{" +
                "name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", budget=" + budget +
                ", concerts=" + concerts +
                '}';
    }
}
