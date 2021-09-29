package es.upm.miw.apaw_practice.domain.models.hotel;

public class Hotel {

    private String name;
    private String direction;
    private Integer numStars;

    public Hotel(){
        //Empty because of framework
    }

    public Hotel(String name, String direction, Integer numStars){
        this.name = name;
        this.direction = direction;
        this.numStars = numStars;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getNumStars() {
        return numStars;
    }

    public void setNumStars(Integer numStars) {
        this.numStars = numStars;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", direction='" + direction + '\'' +
                ", numStars=" + numStars +
                '}';
    }
}
