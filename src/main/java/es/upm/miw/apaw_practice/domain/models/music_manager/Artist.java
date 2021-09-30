package es.upm.miw.apaw_practice.domain.models.music_manager;

public class Artist {
    private String firstName;
    private String familyName;
    private Integer age;

    public Artist() {
        // empty for framework
    }

    public Artist(String firstName, String familyName, Integer age) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "firstName='" + firstName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", age=" + age +
                '}';
    }
}