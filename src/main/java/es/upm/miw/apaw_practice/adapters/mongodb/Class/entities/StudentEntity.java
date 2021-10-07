package es.upm.miw.apaw_practice.adapters.mongodb.Class.entities;

import nonapi.io.github.classgraph.json.Id;

public class StudentEntity {

    @Id
    private String id;
    private String name;
    private int age;
    private boolean Is_spanish;


    public StudentEntity(){
        //empty for framework
    }

    public StudentEntity(String name,int age,boolean Is_spanish){
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

    public String getId(){ return id;}
    public void setId(String id){ this.id = id;}

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((StudentEntity) obj).id));
    }

    @Override
    public String toString(){
        return "Album{" +
                "name =" + name + '\'' +
                ", age =" + age +  '\'' +
                ", Is_spanish ='" + Is_spanish + '\'' +
                '}';
    }
}
