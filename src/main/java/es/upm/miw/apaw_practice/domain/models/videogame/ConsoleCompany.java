package es.upm.miw.apaw_practice.domain.models.videogame;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsoleCompany {
    private String consoleCompany;
    private String website;
    private int numberOfEmployees;
    private boolean active;
    private LocalDate foundationDate;
    private List<Console> consoles;

    private  ConsoleCompany(){
        //empty for framework
    }

    public ConsoleCompany(String consoleCompany,  String website, int numberOfEmployees, boolean active, LocalDate foundationDate, List<Console> consoles) {
        this.consoleCompany = consoleCompany;
        this.website = website;
        this.numberOfEmployees = numberOfEmployees;
        this.active = active;
        this.foundationDate = foundationDate;
        this.consoles = consoles;
    }
    public String getConsoleCompany() {
        return consoleCompany;
    }
    public void setConsoleCompany(String consoleCompany) {
        this.consoleCompany = consoleCompany;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }
    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
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
    public String toString() {
        return "ConsoleCompany{" +
                "consoleCompany='" + consoleCompany + '\'' +
                ", website=" + website +
                ", numberOfEmployees=" + numberOfEmployees +
                ", active=" + active +
                ", foundationDate=" + foundationDate +
                ", consoles=" + consoles +
                '}';
    }
}
