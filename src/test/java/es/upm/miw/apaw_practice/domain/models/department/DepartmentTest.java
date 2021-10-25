package es.upm.miw.apaw_practice.domain.models.department;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class DepartmentTest {

    private TreeDepartment treeDepartment;

    @Test
    void testAddAndTotalDepartmentManager(){
        DepartmentEmployee[] departmentEmployeeList = new DepartmentEmployee[]{
                new DepartmentEmployee("00523821F", LocalDate.of(1984, 8, 27), false)
        };
        Manager[] managers = {
          Manager.builder()
                    .email("nombre@gmail.com")
                    .phoneNumber("432123543")
                    .experienceYears(6)
                    .departmentEmployees(Arrays.asList(departmentEmployeeList))
                    .build(),
            Manager.builder()
                    .email("apellido@gmail.com")
                    .phoneNumber("432123543")
                    .experienceYears(6)
                    .departmentEmployees(Arrays.asList(departmentEmployeeList))
                    .build(),
            Manager.builder()
                    .email("boz@gmail.com")
                    .phoneNumber("432123543")
                    .experienceYears(6)
                    .departmentEmployees(Arrays.asList(departmentEmployeeList))
                    .build(),
        };
        Company company = new Company("madrid", "Z12312345");
        Department[] departments = {
                new Department("HHRR","se encarga de que los empleados esten bien", company, List.of(managers[0])),
                new Department("Software", "Departamento de Software", company, List.of(managers[1])),
                new Department("QA", "Departamento de QA", company, List.of(managers[2]))
        };
        TreeDepartmentLeaf treeDepartmentLeaf = new TreeDepartmentLeaf(departments[0]);
        treeDepartment = treeDepartmentLeaf;
        assertEquals(new BigDecimal(1),treeDepartment.totalDepartmentManager());

        TreeDepartmentComposite treeDepartmentComposite = new TreeDepartmentComposite();
        treeDepartmentComposite.add(new TreeDepartmentLeaf(departments[1]));
        treeDepartmentComposite.add(new TreeDepartmentLeaf(departments[2]));
        treeDepartment = treeDepartmentComposite;
        assertEquals(new BigDecimal(2),treeDepartment.totalDepartmentManager());

        treeDepartmentComposite.add(treeDepartmentLeaf);
        assertEquals(new BigDecimal(3),treeDepartment.totalDepartmentManager());
    }
}
