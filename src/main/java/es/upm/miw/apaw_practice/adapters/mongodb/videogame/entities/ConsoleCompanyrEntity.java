package es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw_practice.domain.models.videogame.Console;
import es.upm.miw.apaw_practice.domain.models.videogame.ConsoleCompany;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
public class ConsoleCompanyrEntity {
    @Id
    private String companyInformation;
    private String website;
    private Integer numberOfEmployee;
    private Boolean active;
    private LocalDate foundationDate;
    private List<ConsoleEntity> consoleEntities;

    public ConsoleCompanyrEntity() {
        //empty for framework
    }

    public ConsoleCompanyrEntity(String companyInformation, String website, Integer numberOfEmployee, Boolean active, LocalDate foundationDate, List<ConsoleEntity> consoleEntities) {
        this.companyInformation = companyInformation;
        this.website = website;
        this.numberOfEmployee = numberOfEmployee;
        this.active = active;
        this.foundationDate = foundationDate;
        this.consoleEntities = consoleEntities;
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
    public Integer getNumberOfEmployee() {
        return numberOfEmployee;
    }
    public void setNumberOfEmployee(Integer numberOfEmployee) {
        this.numberOfEmployee = numberOfEmployee;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public LocalDate getFoundationDate() {
        return foundationDate;
    }
    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }
    public List<ConsoleEntity> getConsoleEntities() {
        return consoleEntities;
    }
    public void setConsoleEntities(List<ConsoleEntity> consoleEntities) {
        this.consoleEntities = consoleEntities;
    }

    public ConsoleCompany toConsoleCompany() {
        ConsoleCompany consoleCompany = new ConsoleCompany();
        BeanUtils.copyProperties(this, consoleCompany, "consoleEntities");
        List<Console> consoles = this.consoleEntities.stream()
                .map(ConsoleEntity::toConsole)
                .toList();
        consoleCompany.setConsoles(consoles);
        return consoleCompany;
    }

    public void fromConsoleCompany(ConsoleCompany consoleCompany) {
        BeanUtils.copyProperties(this, consoleCompany, "consoleEntities");
    }

    @Override
    public String toString() {
        return "ConsoleCompanyEntity{" +
                ", companyInformation=" + companyInformation +
                ", website=" + website +
                ", numberOfEmployee='" + numberOfEmployee + '\'' +
                ", active='" + active + '\'' +
                ", foundationDate='" + foundationDate + '\'' +
                ", consoleEntities='" + consoleEntities + '\'' +
                '}';
    }
}