package es.upm.miw.apaw_practice.domain.models.music_festival;

import java.time.LocalDateTime;
import java.util.Objects;

public class Stage {

    private String name;
    private String location;
    private int capacity;
    private LocalDateTime openTime;

    public Stage() {
        //empty for framework
    }

    public Stage(String name, String location, int capacity, LocalDateTime openTime) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.openTime = openTime;
    }

    public static StageBuilders.Name<Stage> builder() {
        return new Builder();
    }


    public static Stage ofCapacity(Stage stage) {
        Stage stageDto = new Stage();
        stageDto.setCapacity(stage.getCapacity());
        return stageDto;
    }

    public void doDefault() {
        if (Objects.isNull(openTime)) {
            this.openTime = LocalDateTime.now();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", openTime=" + openTime +
                '}';
    }

    public static class Builder implements StageBuilders.Name<Stage>, StageBuilders.Location<Stage>,
            StageBuilders.Capacity<Stage>, StageBuilders.Optionals<Stage> {
        private final Stage stage;

        public Builder() {
            this.stage = new Stage();
        }

        @Override
        public StageBuilders.Location<Stage> name(String name) {
            this.stage.name = name;
            return this;
        }

        @Override
        public StageBuilders.Capacity<Stage> location(String location) {
            this.stage.location = location;
            return this;
        }

        @Override
        public StageBuilders.Optionals<Stage> capacity(int capacity) {
            this.stage.capacity = capacity;
            return this;
        }

        @Override
        public StageBuilders.Optionals<Stage> openTime(LocalDateTime openTime) {
            this.stage.openTime = openTime;
            return this;
        }

        @Override
        public Stage build() {
            return this.stage;
        }


    }
}
