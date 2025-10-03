package es.upm.miw.apaw.adapters.mongodb.sports.academy.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ProfessorEntity {
    @Id
    private UUID userDtoId;
    private String specialization;
    private String licenseNumber;

    public ProfessorEntity(Professor professor) {
        BeanUtils.copyProperties(professor, this);
        userDtoId = professor.getUser().getId();
    }

    public void fromProfessor(Professor professor) {
        BeanUtils.copyProperties(professor, this);
        userDtoId = professor.getUser().getId();
    }

    public Professor toProfessor() {
        Professor professor = new Professor();
        BeanUtils.copyProperties(this, professor);
        professor.setUser(UserDto.builder().id(userDtoId).build());
        return professor;
    }
}
