package es.upm.miw.apaw_practice.adapters.mongodb.training.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LecturerEntity {
    @Id
    private String id;
    private String name;
    private String dni;
    private String phone;

    public LecturerEntity(){
        //empty from framework
    }

    public LecturerEntity(String name, String dni, String phone) {
        this.name = name;
        this.dni = dni;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (dni.equals(((es.upm.miw.apaw_practice.adapters.mongodb.training.entities.LecturerEntity) obj).dni));
    }

    @Override
    public String toString() {
        return "LecturerEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
