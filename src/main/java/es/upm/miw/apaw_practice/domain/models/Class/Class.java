package es.upm.miw.apaw_practice.domain.models.Class;


import java.time.LocalDate;

public class Class {

    private String name;
    private int credit;
    private LocalDate StartTime;

    public Class() {
        //empty for framework
    }

    public Class(String name, int credit, LocalDate StartTime) {
        this.name = name;
        this.credit = credit;
        this.StartTime = StartTime;
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
        return StartTime;
    }

    public void setStartTime(LocalDate StartTime) {
        this.StartTime = StartTime;
    }

    @Override
    public String toString() {
        return "Class {" +
                "name =" + name + '\'' +
                ", credit =" + credit + '\'' +
                ", StartTime=" + StartTime + '\'' +
                '}';
    }

}
