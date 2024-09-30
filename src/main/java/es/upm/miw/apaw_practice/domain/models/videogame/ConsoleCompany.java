package es.upm.miw.apaw_practice.domain.models.videogame;

import java.util.ArrayList;
import java.util.List;

public class ConsoleCompany {
    private String name;
    private String headquarter;
    private String website;
    private List<Console> consoles;

    private  ConsoleCompany(){
        //empty for framework
    }

    public ConsoleCompany(String name, String headquarter, String website, List<Console> consoles) {
        this.name = name;
        this.headquarter = headquarter;
        this.website = website;
        this.consoles = consoles;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHeadquarter() {
        return headquarter;
    }
    public void setHeadquarter(String headquarter) {
        this.headquarter = headquarter;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
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
                "name='" + name + '\'' +
                ", headquarter=" + headquarter +
                ", website=" + website +
                ", consoles=" + consoles +
                '}';
    }
}
