package es.upm.miw.apaw_practice.domain.models.Class;

public class Learner {

    private String name;
    private int age;
    private boolean isSpanish;

    public Learner() {
        //empty for framework
    }

    public Learner(String name, int age, boolean isSpanish) {
        this.name = name;
        this.age = age;
        this.isSpanish = isSpanish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getSpanish() {
        return isSpanish;
    }

    public void setSpanish(boolean isSpanish) {
        this.isSpanish = isSpanish;
    }

    @Override
    public String toString() {
        return "Learner {" +
                "name =" + name + '\'' +
                ", age =" + age + '\'' +
                ", Is_spanish ='" + isSpanish + '\'' +
                '}';
    }
}
