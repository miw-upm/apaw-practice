package es.upm.miw.apaw_practice.domain.models.Class;

public class Course {

    private String name;
    private int credit;
    private int NumberOfStudents;

    public Course(){
        //empty for framework
    }

    public Course(String name,int credit,int NumberOfStudents){
        this.name = name;
        this.credit = credit;
        this.NumberOfStudents = NumberOfStudents;
    }

    public String getName(){ return name;}
    public void setName(String name){ this.name = name;}

    public int getCredit(){ return credit;}
    public void setCredit(int credit){ this.credit = credit;}

    public int getNumberOfStudents(){ return NumberOfStudents;}
    public void setNumberOfStudents(int NumberOfStudents){ this.NumberOfStudents = NumberOfStudents;}


}
