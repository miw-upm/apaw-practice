package es.upm.miw.apaw_practice.domain.models.martial_art;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MartialArtsClass {
    private String name;
    private LocalDate startDate;
    private String academy;
    private List<Technique> techniques;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public List<Technique> getTechniques() {
        return techniques;
    }

    public void setTechniques(List<Technique> techniques) {
        this.techniques = techniques;
    }
    public MartialArtsClass(String name, LocalDate startDate, String academy, List<Technique> techniques) {
        this.name = name;
        this.startDate = startDate;
        this.academy = academy;
        this.techniques = techniques;
    }

    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", academy='" + academy + '\'' +
                ", techniques=" + techniques +
                '}';
    }
}
