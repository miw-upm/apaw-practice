package es.upm.miw.apaw_practice.domain.models.department;

import java.util.List;

public interface ManagerBuilder {

    interface Email {
        PhoneNumber email(String email);
    }

    interface  PhoneNumber{
        ExperienceYears phoneNumber(String phoneNumber);
    }

    interface ExperienceYears{
        DepartmentEmployees experienceYears(Integer experienceYears);
    }

    interface DepartmentEmployees{
        Optionals departmentEmployees(List<DepartmentEmployee> departmentEmployeeList);
    }

    interface Optionals {
        Manager build();
    }
}
