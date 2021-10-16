package es.upm.miw.apaw_practice.domain.models.videogame;

public class Console {

    private String consoleName;
    private String model;
    private String color;
    private String memory;

    public Console(String consoleName, String model, String color, String memory) {
        this.consoleName = consoleName;
        this.model = model;
        this.color = color;
        this.memory = memory;
    }

    public String getConsoleName() {
        return consoleName;
    }

    public void setConsoleName(String consoleName) {
        this.consoleName = consoleName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "Console{" +
                "consoleName='" + consoleName + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }
}
