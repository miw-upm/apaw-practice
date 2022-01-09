package es.upm.miw.apaw_practice.domain.models.training;

public class TrainingItemUpdating {
    private String name;

    public TrainingItemUpdating() {
        //empty from framework
    }

    public TrainingItemUpdating(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TrainingItemUpdating{" +
                "name='" + name + '\'' +
                '}';
    }
}
