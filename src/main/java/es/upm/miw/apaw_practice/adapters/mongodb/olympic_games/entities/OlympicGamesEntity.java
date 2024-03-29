package es.upm.miw.apaw_practice.adapters.mongodb.olympic_games.entities;

import es.upm.miw.apaw_practice.domain.models.olympic_games.Discipline;
import es.upm.miw.apaw_practice.domain.models.olympic_games.OlympicGames;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class OlympicGamesEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private Integer edition;
    private String hostingPlace;
    private LocalDate startDate;
    private Boolean summerGames;
    @DBRef
    private List<DisciplineEntity> disciplinesEntities;

    public OlympicGamesEntity() {
    }

    public OlympicGamesEntity(Integer edition, String hostingPlace, LocalDate startDate, Boolean summerGames, List<DisciplineEntity> disciplinesEntities) {
        this.id = UUID.randomUUID().toString();
        this.edition = edition;
        this.hostingPlace = hostingPlace;
        this.startDate = startDate;
        this.summerGames = summerGames;
        this.disciplinesEntities = disciplinesEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public String getHostingPlace() {
        return hostingPlace;
    }

    public void setHostingPlace(String hostingPlace) {
        this.hostingPlace = hostingPlace;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Boolean getSummerGames() {
        return summerGames;
    }

    public void setSummerGames(Boolean summerGames) {
        this.summerGames = summerGames;
    }

    public List<DisciplineEntity> getDisciplinesEntities() {
        return disciplinesEntities;
    }

    public void setDisciplinesEntities(List<DisciplineEntity> disciplinesEntities) {
        this.disciplinesEntities = disciplinesEntities;
    }

    public OlympicGames toOlympicGames() {
        List<Discipline> disciplines = this.disciplinesEntities.stream()
                .map(DisciplineEntity::toDiscipline)
                .toList();
        return new OlympicGames(edition, hostingPlace, startDate, summerGames, disciplines);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (edition.equals(((OlympicGamesEntity) obj).edition));
    }

    @Override
    public int hashCode() {
        return edition.hashCode();
    }

    @Override
    public String toString() {
        return "OlympicGamesEntity{" +
                "id='" + id + '\'' +
                ", edition=" + edition +
                ", hostingPlace='" + hostingPlace + '\'' +
                ", startDate=" + startDate +
                ", summerGames=" + summerGames +
                ", disciplines=" + disciplinesEntities +
                '}';
    }
}


