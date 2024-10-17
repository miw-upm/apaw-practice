package es.upm.miw.apaw_practice.adapters.rest.company;

import es.upm.miw.apaw_practice.domain.models.company.Department;
import es.upm.miw.apaw_practice.domain.services.company.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DepartmentResource.DEPARTMENTS)
public class DepartmentResource {
    static final String NAME_NAME = "/{departmentName}";
    static final String DEPARTMENTS = "/company/departments";
    static final String EMPLOYEE_COUNT = "/employee-count";

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PutMapping(NAME_NAME + EMPLOYEE_COUNT)
    public Department updateEmployeeCount(@PathVariable String departmentName, @RequestBody Integer employeeCount) {
        return this.departmentService.updateEmployeeCount(departmentName, employeeCount);
    }
}



