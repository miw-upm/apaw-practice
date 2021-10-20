package es.upm.miw.apaw_practice.adapters.rest.department;

import es.upm.miw.apaw_practice.domain.models.department.DepartmentEmployee;
import es.upm.miw.apaw_practice.domain.models.department.DepartmentEmployeeIsActiveUpdating;
import es.upm.miw.apaw_practice.domain.services.department.DepartmentEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping
    public void updateIsActive(@RequestBody List<DepartmentEmployeeIsActiveUpdating> employeeIsActiveUpdatingList) {
        this.departmentEmployeeService.updateIsActive(employeeIsActiveUpdatingList.stream());
    }
}
