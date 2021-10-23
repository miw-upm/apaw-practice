package es.upm.miw.apaw_practice.domain.models.university;

public interface ClassroomBuilders {

    interface School {
        Number school(String school);
    }

    interface Number {
        Capacity number(Integer number);
    }

    interface Capacity {
        Build capacity(Integer capacity);
    }

    interface Build {
        Classroom build();
    }

}
