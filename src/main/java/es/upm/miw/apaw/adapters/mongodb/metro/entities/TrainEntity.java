package es.upm.miw.apaw.adapters.mongodb.metro.entities;

import es.upm.miw.apaw.domain.models.metro.Train;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class TrainEntity {

    @Id
    private UUID id;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private Integer numCars;

    private Boolean operational;

    private Double maxSpeed;

    public TrainEntity(Train train){
        BeanUtils.copyProperties(train, this);
    }

    public Train toTrain(){
        Train train = new Train();
        BeanUtils.copyProperties(this, train);
        return train;
    }


}
