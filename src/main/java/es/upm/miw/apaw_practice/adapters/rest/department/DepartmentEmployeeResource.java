package es.upm.miw.apaw_practice.adapters.rest.department;

import es.upm.miw.apaw_practice.domain.models.department.DepartmentEmployee;
import es.upm.miw.apaw_practice.domain.services.department.DepartmentEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DepartmentEmployeeResource.DEPARTMENT_EMPLOYEES)
public class DepartmentEmployeeResource {
    static final String DEPARTMENT_EMPLOYEES = "/department/department-employees";

    private final DepartmentEmployeeService departmentEmployeeService;

    @Autowired
    public DepartmentEmployeeResource(DepartmentEmployeeService departmentEmployeeService) {
        this.departmentEmployeeService = departmentEmployeeService;
    }

    @PostMapping
    public DepartmentEmployee create(@RequestBody DepartmentEmployee departmentEmployee){
        return this.departmentEmployeeService.create(departmentEmployee);
    }
}
