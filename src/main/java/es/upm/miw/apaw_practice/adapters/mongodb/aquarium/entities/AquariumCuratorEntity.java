package es.upm.miw.apaw_practice.adapters.mongodb.aquarium.entities;
import es.upm.miw.apaw_practice.domain.models.aquarium.Aquarium;
import es.upm.miw.apaw_practice.domain.models.aquarium.AquariumCurator;
import es.upm.miw.apaw_practice.domain.models.aquarium.Fishpond;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Document
public class AquariumCuratorEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String position;
    private Boolean vacationState;
    private List<FishpondEntity> fishpondEntities;
    private AquariumEntity aquariumEntity;

    public AquariumCuratorEntity() {
        //empty for framework
    }

    public AquariumCuratorEntity(String name, String position, Boolean vacationState, List<FishpondEntity> fishpondEntities, AquariumEntity aquariumEntity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.position = position;
        this.vacationState = vacationState;
        this.fishpondEntities = fishpondEntities;
        this.aquariumEntity = aquariumEntity;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean isVacationState() {
        return vacationState;
    }

    public void setVacationState(Boolean vacationState) {
        this.vacationState = vacationState;
    }

    public List<FishpondEntity> getFishpondEntities() {
        return fishpondEntities;
    }

    public void setFishpondEntities(List<FishpondEntity> fishpondEntities) {
        this.fishpondEntities = fishpondEntities;
    }

    public AquariumEntity getAquariumEntity() {
        return aquariumEntity;
    }

    public void setAquariumEntity(AquariumEntity aquariumEntity) {
        this.aquariumEntity = aquariumEntity;
    }
    public FishpondEntity getFishpondEntity() {
        return (FishpondEntity) fishpondEntities;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AquariumCuratorEntity that)) return false;
        return (Objects.equals(getId(), that.getId()) || Objects.equals(getName(), that.getName())
                &&Objects.equals(getPosition(), that.getPosition()))
                && Objects.equals(getAquariumEntity(), that.getAquariumEntity())
                &&Objects.equals(getFishpondEntities(), that.getFishpondEntities());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "AquariumCuratorEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", vacationState=" + vacationState +
                ", fishponds=" + fishpondEntities +
                ", aquarium=" + aquariumEntity +
                '}';
    }



    public AquariumCurator toAquariumCurator() {
        Aquarium aquarium = this.aquariumEntity.toAquarium();
        List<Fishpond> fishponds =this.fishpondEntities.stream()
                .map(FishpondEntity::toFishpond)
                .toList();
        return new AquariumCurator(this.name,this.position,this.vacationState, fishponds, aquarium);
    }



}