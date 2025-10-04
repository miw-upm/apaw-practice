package es.upm.miw.apaw.adapters.mongodb.fighters.entities;

import es.upm.miw.apaw.domain.models.fighters.Coach;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class CoachEntity {
    @Id
    @EqualsAndHashCode.Include
    private UUID coachNumber;
    private String fullName;
    private String academy;
    private Integer experienceYears;

    public CoachEntity(Coach coach) {
        BeanUtils.copyProperties(coach, this);
        this.coachNumber = UUID.randomUUID();
    }

    public void fromCoach(Coach coach){
        BeanUtils.copyProperties(coach,this);
    }

    public Coach toCoach(){
        Coach coach = new Coach();
        BeanUtils.copyProperties(this, coach);
        return coach;
    }

}
