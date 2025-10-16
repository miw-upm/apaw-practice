package es.upm.miw.apaw.adapters.mongodb.metro.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.metro.TrainLine;
import es.upm.miw.apaw.domain.models.metro.TrainStation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class TrainStationEntity {

    @Id
    private String name;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private Integer capacity;

    private String location;

    private Boolean multipleLines;

    private LocalDate inaugurationDate;

    @DBRef
    private List<TrainLineEntity> trainLineEntities;
    private ZoneEntity zoneEntity;
    private List<UUID> usersTrainStation;


    public TrainStation toTrainStation() {
        TrainStation trainStation = new TrainStation();
        BeanUtils.copyProperties(this, trainStation, "usersTrainStation", "trainLineEntities", "zone");
        List<UserDto> users = this.usersTrainStation
                .stream()
                .map(user -> UserDto.builder().id(user).build())
                .toList();
        trainStation.setUsers(users);

        List<TrainLine> trainLines = this.trainLineEntities.stream()
                .map(TrainLineEntity::toTrainLine)
                .toList();
        trainStation.setTrainLines(trainLines);
        trainStation.setZone(this.zoneEntity.toZone());

        return trainStation;
    }
}

