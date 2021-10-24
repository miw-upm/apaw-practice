package es.upm.miw.apaw_practice.domain.models.videogame;

public class PlatformMemoryUpdating {

    private String consoleName;
    private String memory;

    public PlatformMemoryUpdating(String consoleName, String memory) {
        this.consoleName = consoleName;
        this.memory = memory;
    }

    public String getConsoleName() {
        return consoleName;
    }

    public void setConsoleName(String consoleName) {
        this.consoleName = consoleName;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "PlatformMemoryUpdating{" +
                "consoleName='" + consoleName + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }
}
