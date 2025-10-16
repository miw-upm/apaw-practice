package es.upm.miw.apaw.adapters.mongodb.metro.entities;

import es.upm.miw.apaw.domain.models.metro.Train;
import es.upm.miw.apaw.domain.models.metro.TrainLine;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class TrainLineEntity {

    @Id
    private Integer number;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String color;

    private Integer numStations;

    private Boolean circular;

    @DBRef
    private List<TrainEntity> trainEntities;

    public TrainLineEntity(TrainLine trainLine){
        BeanUtils.copyProperties(trainLine, this, "trainEntities");
        this.trainEntities = trainLine.getTrains().stream()
                .map(TrainEntity::new)
                .toList();
    }

    public TrainLine toTrainLine(){
        TrainLine trainLine = new TrainLine();
        BeanUtils.copyProperties(this, trainLine, "trainEntities");
        List<Train> trains = this.trainEntities.stream()
                .map(TrainEntity::toTrain)
                .toList();
        trainLine.setTrains(trains);
        return trainLine;
    }
}
