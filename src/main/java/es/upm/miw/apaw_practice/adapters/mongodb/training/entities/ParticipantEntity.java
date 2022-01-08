package es.upm.miw.apaw_practice.adapters.mongodb.training.entities;

import es.upm.miw.apaw_practice.domain.models.training.Participant;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ParticipantEntity {
    @Id
    private String id;
    private String name;
    private Boolean graduate;
    @Indexed(unique = true)
    private String dni;
    private String phone;
    private String email;

    public ParticipantEntity(String name, Boolean graduate, String dni, String phone, String email) {
        this.name = name;
        this.graduate = graduate;
        this.dni = dni;
        this.phone = phone;
        this.email = email;
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

    public Boolean getGraduate() {
        return graduate;
    }

    public void setGraduate(Boolean graduate) {
        this.graduate = graduate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Participant toParticipant() {
        Participant participant = new Participant();
        BeanUtils.copyProperties(this, participant);
        return participant;
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (dni.equals(((es.upm.miw.apaw_practice.adapters.mongodb.training.entities.ParticipantEntity) obj).dni));
    }

    @Override
    public String toString() {
        return "ParticipantEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", graduate=" + graduate +
                ", dni='" + dni + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
