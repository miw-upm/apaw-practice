package es.upm.miw.apaw_practice.domain.models.videogame;

public class ConsoleCompanyActivedUpdating {
    private String companyInformation;
    private Boolean actived;

    public ConsoleCompanyActivedUpdating() {
        //empty for framework
    }
    public ConsoleCompanyActivedUpdating(String companyInformation, Boolean actived) {
        this.companyInformation = companyInformation;
        this.actived = actived;
    }
    public String getCompanyInformation() {
        return companyInformation;
    }
    public void setCompanyInformation(String companyInformation) {
        this.companyInformation = companyInformation;
    }
    public Boolean getActived() {
        return actived;
    }
    public void setActived(Boolean actived) {
        this.actived = actived;
    }
    @Override
    public String toString() {
        return "ConsoleCompanyActiveUpdating{" +
                "consoleInformation=" + companyInformation +
                ", actived" + actived +
                '}';
    }
}