package es.upm.miw.apaw_practice.domain.models.Hospital;

public class Hospital{
    private String name;
    private Integer capacity;

    private  String location;

    private Long id;

    public Hospital() {
    }
    public Hospital(String name, Integer capacity, string location, id Long){
        this.name=name;
        this.capacity=capacity;
        this.location=location;
        this.id=id

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", location='" + location + '\'' +
                ", id=" + id +
                '}';
    }
}
