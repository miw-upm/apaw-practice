package es.upm.miw.apaw_practice.domain.models.gym;

public class Coach {
    private String dni;
    private String firstName;
    private String lastName;
    private Integer phone;
    private Lesson lesson;



    public Coach(){
        //empty for framework
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "dni='" + dni + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                ", lesson=" + lesson +
                '}';
    }

    public static CoachBuilders.Dni builder() {
        return new Builder();
    }

    public static class Builder implements CoachBuilders.Dni, CoachBuilders.FirstName, CoachBuilders.LastName, CoachBuilders.Phone,
            CoachBuilders.Lessons, CoachBuilders.Optionals {
        private Coach coach;

        public Builder() {
            this.coach = new Coach();
        }


        @Override
        public CoachBuilders.FirstName dni(String dni) {
            this.coach.dni = dni;
            return this;
        }

        @Override
        public CoachBuilders.LastName firstName(String firstName) {
            this.coach.firstName = firstName;
            return this;
        }

        @Override
        public CoachBuilders.Phone lastname(String lastname) {
            this.coach.lastName = lastname;
            return this;
        }

        @Override
        public CoachBuilders.Lessons phone(Integer phone) {
            this.coach.phone = phone;
            return this;
        }

        @Override
        public CoachBuilders.Optionals lessons(Lesson lesson) {
            this.coach.lesson = lesson;
            return this;
        }

        @Override
        public Coach bulid() {
            return this.coach;
        }
    }

}



