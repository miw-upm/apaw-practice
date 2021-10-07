package es.upm.miw.apaw_practice.adapters.mongodb.Class.entities;

import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.FootballPlayerEntity;
import nonapi.io.github.classgraph.json.Id;

import java.time.LocalDate;

public class ClassEntity {

    @Id
    private String id;
    private String name;
    private int credit;
    private LocalDate StartTime;

    public ClassEntity(){
        //empty for framework
    }

    public ClassEntity(String name,int credit,LocalDate StartTime){
        this.name = name;
        this.credit = credit;
        this.StartTime = StartTime;
    }

    public String getName(){ return name;}
    public void setName(String name){ this.name = name;}

    public int getCredit(){ return credit;}
    public void setCredit(int credit){ this.credit = credit;}

    public LocalDate getStartTime(){ return StartTime;}
    public void setStartTime(LocalDate StartTime){ this.StartTime = StartTime;}

    public String getId(){ return id;}
    public void setId(String id){ this.id = id;}

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ClassEntity) obj).id));
    }

    @Override
    public String toString(){
        return "Album{" +
                "name =" + name + '\'' +
                ", credit =" + credit +  '\'' +
                ", StartTime=" + StartTime + '\'' +
                '}';
    }

}
