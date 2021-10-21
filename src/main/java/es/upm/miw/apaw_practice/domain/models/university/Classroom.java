package es.upm.miw.apaw_practice.domain.models.university;

public class Classroom {

    private String school;
    private Integer number;
    private Integer capacity;

    public Classroom() {
        //empty for framework
    }

    public static ClassroomBuilders.School builder() {
        return new Builder();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public static class Builder implements ClassroomBuilders.School, ClassroomBuilders.Number, ClassroomBuilders.Capacity, ClassroomBuilders.Build {

        private final Classroom classroom;

        public Builder() {
            this.classroom = new Classroom();
        }

        @Override
        public ClassroomBuilders.Number school(String school) {
            this.classroom.setSchool(school);
            return this;
        }

        @Override
        public ClassroomBuilders.Capacity number(Integer number) {
            this.classroom.setNumber(number);
            return this;
        }

        @Override
        public ClassroomBuilders.Build capacity(Integer capacity) {
            this.classroom.setCapacity(capacity);
            return this;
        }

        @Override
        public Classroom build() {
            return this.classroom;
        }
    }

}
