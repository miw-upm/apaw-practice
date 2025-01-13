package es.upm.miw.apaw_practice.domain.models.music_festival;

import java.time.LocalDateTime;

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
}
