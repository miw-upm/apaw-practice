package es.upm.miw.apaw_practice.adapters.mongodb.Class.entities;

import es.upm.miw.apaw_practice.domain.models.Class.Class;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;


@Document
public class ClassEntity {

    @Id
    private String id;
    private String name;
    private int credit;
    private LocalDate startTime;

    public ClassEntity() {
        //empty for framework
    }

    public ClassEntity(String name, int credit, LocalDate StartTime) {
        this.name = name;
        this.credit = credit;
        this.startTime = StartTime;
    }

    public ClassEntity(Class myclass){
        BeanUtils.copyProperties(myclass,this);
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate StartTime) {
        this.startTime = StartTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class toClass() {
        Class class1 = new Class();
        BeanUtils.copyProperties(this, class1);
        return class1;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ClassEntity) obj).id));
    }

    @Override
    public String toString() {
        return "Class{" +
                "name =" + name + '\'' +
                ", credit =" + credit + '\'' +
                ", startTime=" + startTime + '\'' +
                '}';
    }

}
