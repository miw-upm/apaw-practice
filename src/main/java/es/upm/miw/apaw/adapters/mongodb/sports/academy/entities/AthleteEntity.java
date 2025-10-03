package es.upm.miw.apaw.adapters.mongodb.sports.academy.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.models.sports.academy.LegalGuardian;
import es.upm.miw.apaw.domain.models.sports.academy.SportModality;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class AthleteEntity {
    @Id
    private UUID userDtoId;
    private int gender;
    private double height;
    private double weight;
    private LocalDate birthDate;
    private List<SportModalityEntity> sportModalities;
    private List<LegalGuardianEntity> legalGuardians;

    public AthleteEntity(Athlete athlete) {
        BeanUtils.copyProperties(athlete, this);
        userDtoId = athlete.getUser().getId();
        sportModalities = athlete.getSportModalities().stream()
                .map(SportModalityEntity::new)
                .toList();
        legalGuardians = athlete.getLegalGuardians().stream()
                .map(LegalGuardianEntity::new)
                .toList();
    }

    public void fromAthlete(Athlete athlete) {
        BeanUtils.copyProperties(athlete, this);
        userDtoId = athlete.getUser().getId();
        sportModalities = athlete.getSportModalities().stream()
                .map(SportModalityEntity::new)
                .toList();
        legalGuardians = athlete.getLegalGuardians().stream()
                .map(LegalGuardianEntity::new)
                .toList();
    }

    public Athlete toAthlete() {
        Athlete athlete = new Athlete();
        BeanUtils.copyProperties(this, athlete);
        athlete.setUser(UserDto.builder().id(userDtoId).build());
        List<SportModality> sportModalitiesList = this.sportModalities.stream()
                .map(SportModalityEntity::toSportModality)
                .toList();
        athlete.setSportModalities(sportModalitiesList);
        List<LegalGuardian> legalGuardiansList = this.legalGuardians.stream()
                .map(LegalGuardianEntity::toLegalGuardian)
                .toList();
        athlete.setLegalGuardians(legalGuardiansList);
        return athlete;
    }
}
