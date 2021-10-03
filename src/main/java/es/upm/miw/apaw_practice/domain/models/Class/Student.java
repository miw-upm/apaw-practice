package es.upm.miw.apaw_practice.domain.models.Class;

public class Student {

    private String name;
    private int age;
    private boolean Is_spanish;


    public Student(){
        //empty for framework
    }

    public Student(String name,int age,boolean Is_spanish){
        this.name = name;
        this.age = age;
        this.Is_spanish = Is_spanish;
    }

    public String getName(){ return name;}
    public void setName(String name){ this.name = name;}

    public int getAge(){return age;}
    public void setAge(int age){ this.age = age;}

    public boolean getIs_spanish(){ return Is_spanish; }
    public void setIs_spanish(boolean Is_spanish){ this.Is_spanish = Is_spanish;}

    @Override
    public String toString(){
        return "Album{" +
                "name =" + name + '\'' +
                ", age =" + age +  '\'' +
                ", Is_spanish ='" + Is_spanish + '\'' +
                '}';
    }
}
