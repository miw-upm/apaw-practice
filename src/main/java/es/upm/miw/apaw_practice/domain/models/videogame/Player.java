package es.upm.miw.apaw_practice.domain.models.videogame;

public class Player {
    private String name;
    private int age;
    private String country;
    private Console console;

    private  Player(){
        //empty for framework
    }

    public Player(String name, int age, String country, Console console){
        this.name = name;
        this.age = age;
        this.country = country;
        this.console = console;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Console getConsole() {
        return console;
    }
    public void setConsole(Console console) {
        this.console = console;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country=" + country +
                ", console=" + console +
                '}';
    }
}
