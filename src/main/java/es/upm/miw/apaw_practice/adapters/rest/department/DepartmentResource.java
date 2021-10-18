package es.upm.miw.apaw_practice.adapters.rest.department;

import es.upm.miw.apaw_practice.domain.models.department.Department;
import es.upm.miw.apaw_practice.domain.services.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(DepartmentResource.DEPARTMENTS)
public class DepartmentResource {

    static final String DEPARTMENTS = "/department/departments";

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public Stream<Department> readAll() {
        return this.departmentService.readAll();
    }
}
