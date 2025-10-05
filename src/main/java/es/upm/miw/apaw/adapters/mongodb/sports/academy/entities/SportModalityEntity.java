package es.upm.miw.apaw.adapters.mongodb.sports.academy.entities;

import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.models.sports.academy.SportModality;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Level;
import es.upm.miw.apaw.domain.models.sports.academy.enums.TargetAudience;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sports_academy_sport_modalities")
public class SportModalityEntity {
    @Id
    private UUID sportId;
    private String title;
    private int level;
    private int targetAudience;
    private boolean active;
    @DBRef
    private List<AthleteEntity> athletes;
    @DBRef
    private ProfessorEntity professor;

    public SportModalityEntity(SportModality sportModality) {
        BeanUtils.copyProperties(sportModality, this);
        athletes = sportModality.getAthletes().stream()
                .map(AthleteEntity::new)
                .toList();
        targetAudience = sportModality.getTargetAudience().getValue();
        professor = new ProfessorEntity(sportModality.getProfessor());
        level = sportModality.getLevel().getValue();
    }

    public void fromSportModality(SportModality sportModality) {
        BeanUtils.copyProperties(sportModality, this);
        athletes = sportModality.getAthletes().stream()
                .map(AthleteEntity::new)
                .toList();
        targetAudience = sportModality.getTargetAudience().getValue();
        professor = new ProfessorEntity(sportModality.getProfessor());
        level = sportModality.getLevel().getValue();
    }

    public SportModality toSportModality() {
        SportModality sportModality = new SportModality();
        BeanUtils.copyProperties(this, sportModality);
        List<Athlete> athleteList = this.athletes.stream()
                .map(AthleteEntity::toAthlete)
                .toList();
        sportModality.setAthletes(athleteList);
        sportModality.setProfessor(this.professor.toProfessor());
        sportModality.setTargetAudience(TargetAudience.values()[this.targetAudience]);
        sportModality.setLevel(Level.values()[this.level]);
        return sportModality;
    }
}
