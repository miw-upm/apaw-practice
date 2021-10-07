package es.upm.miw.apaw_practice.adapters.mongodb.Class.entities;

import nonapi.io.github.classgraph.json.Id;

import java.time.LocalDate;

public class ProfessorEntity {

    @Id
    private String id;
    private String name;
    private String course;
    private int age;
    private LocalDate EntryDate;

    public ProfessorEntity(){
        //empty for framework
    }

    public ProfessorEntity(String name,String course,int age,LocalDate EntryDate){
        this.name = name;
        this.course = course;
        this.age = age;
        this.EntryDate = EntryDate;
    }

    public String getName(){ return name;}
    public void setName(String name){ this.name = name;}

    public String getCourse(){ return course;}
    public void setCourse(String course){ this.course = course;}

    public int getAge(){ return age;}
    public void setAge(int age){ this.age = age;}

    public LocalDate getEntryData(){ return EntryDate;}
    public void setEntryDate(LocalDate EntryData){ this.EntryDate = EntryData;}

    public String getId(){ return id;}
    public void setId(String id){ this.id = id;}

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ProfessorEntity) obj).id));
    }

    @Override
    public String toString(){
        return "Album{" +
                "name =" + name + '\'' +
                ", course =" + course +  '\'' +
                ", age =" + age + '\'' +
                ", EntryDate =" + EntryDate + '\'' +
                '}';
    }

}
