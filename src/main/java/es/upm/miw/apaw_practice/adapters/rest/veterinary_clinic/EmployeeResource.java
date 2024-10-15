package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Employee;
import es.upm.miw.apaw_practice.domain.services.veterinary_clinic.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EmployeeResource.EMPLOYEES)
public class EmployeeResource {

    static final String EMPLOYEES = "/veterinary-clinic/employees";
    static final String NAME_EMPLOYEE = "/{employeeName}";

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(NAME_EMPLOYEE)
    public Employee read(@PathVariable String employeeName) {
        return Employee.ofAnimalName(this.employeeService.read(employeeName));
    }

    @DeleteMapping(NAME_EMPLOYEE)
    public void delete(@PathVariable String employeeName) {
        this.employeeService.delete(employeeName);
    }
}