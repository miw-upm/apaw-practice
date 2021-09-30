package es.upm.miw.apaw_practice.domain.models.music_manager;

public class Artist {
    private String name;
    private String familyName;
    private int age;

    public Artist(String name, String familyName, int age) {
        this.name = name;
        this.familyName = familyName;
        this.age = age;
    }

    public Artist() {
        // empty for framework
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", age=" + age +
                '}';
    }
}
