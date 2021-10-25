package es.upm.miw.apaw_practice.domain.models.videogame;

public class Platform {

    private String consoleName;
    private String model;
    private String memory;

    public Platform() {
        //empty from framework
    }

    public Platform(String consoleName, String model, String memory) {
        this.consoleName = consoleName;
        this.model = model;
        this.memory = memory;
    }

    public static Platform ofPlatform(Platform platform) {
        Platform platformDto = new Platform();
        platformDto.setConsoleName(platform.getConsoleName());
        platformDto.setMemory(platform.getMemory());
        platformDto.setModel(platform.getModel());
        return platformDto;
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

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "consoleName='" + consoleName + '\'' +
                ", model='" + model + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }
}
