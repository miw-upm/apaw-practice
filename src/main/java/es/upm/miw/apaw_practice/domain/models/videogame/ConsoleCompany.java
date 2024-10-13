package es.upm.miw.apaw_practice.domain.models.videogame;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConsoleCompany {
    private String companyInformation;
    private String website;
    private int numberOfEmployee;
    private boolean active;
    private LocalDate foundationDate;
    private List<Console> consoles;

    private  ConsoleCompany(){
        //empty for framework
    }

    public ConsoleCompany(String companyInformation,  String website, int numberOfEmployee, boolean active, LocalDate foundationDate, List<Console> consoles) {
        this.companyInformation = companyInformation;
        this.website = website;
        this.numberOfEmployee = numberOfEmployee;
        this.active = active;
        this.foundationDate = foundationDate;
        this.consoles = consoles;
    }
    public String getCompanyInformation() {
        return companyInformation;
    }
    public void setCompanyInformation(String companyInformation) {
        this.companyInformation = companyInformation;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public int getNumberOfEmployee() {
        return numberOfEmployee;
    }
    public void setNumberOfEmployee(int numberOfEmployee) {
        this.numberOfEmployee = numberOfEmployee;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public LocalDate getFoundationDate() {
        return foundationDate;
    }
    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }
    public List<Console> getConsoles() {
        return consoles;
    }
    public void setConsoles(List<Console> consoles) {
        this.consoles = consoles;
    }
    public void addConsole(Console console){
        this.consoles.add(console);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsoleCompany consoleCompany = (ConsoleCompany) o;
        return Objects.equals(companyInformation, consoleCompany.companyInformation) && Objects.equals(website, consoleCompany.website) && Objects.equals(numberOfEmployee, consoleCompany.numberOfEmployee) && Objects.equals(active, consoleCompany.active) && Objects.equals(foundationDate, consoleCompany.foundationDate) && Objects.equals(consoles, consoleCompany.consoles);
    }

    @Override
    public int hashCode(){
        return Objects.hash(companyInformation, website, numberOfEmployee, active, foundationDate, consoles);
    }

    @Override
    public String toString() {
        return "ConsoleCompany{" +
                "companyInformation='" + companyInformation + '\'' +
                ", website=" + website +
                ", numberOfEmployee=" + numberOfEmployee +
                ", active=" + active +
                ", foundationDate=" + foundationDate +
                ", consoles=" + consoles +
                '}';
    }
}
