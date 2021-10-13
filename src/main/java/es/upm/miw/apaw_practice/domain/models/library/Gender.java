package es.upm.miw.apaw_practice.domain.models.library;

public enum Gender {

    M("Male"), F("Female");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String get() {
        return gender;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "gender='" + gender + '\'' +
                '}';
    }
}
