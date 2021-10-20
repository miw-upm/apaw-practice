package es.upm.miw.apaw_practice.adapters.mongodb.department;

import es.upm.miw.apaw_practice.adapters.mongodb.department.daos.CompanyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.department.daos.DepartmentEmployeeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.department.daos.DepartmentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.department.daos.ManagerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.CompanyEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.DepartmentEmployeeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.DepartmentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.ManagerEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DepartmentSeederService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Department Initial Load -----------");
        CompanyEntity[] companyEntities = {
                new CompanyEntity("Calle del Dr Cortezo, 6, 28012 Madrid", "A12312345"),
                new CompanyEntity("Calle de Luchana, 15, 28010 Madrid","B12312345"),
                new CompanyEntity("C. de Alan Turing, s/n, 28031 Madrid","C32132154"),

        };
        this.companyRepository.saveAll(Arrays.asList(companyEntities));
        DepartmentEmployeeEntity[] departmentEmployeeEntities = {
                new DepartmentEmployeeEntity("00523821F", LocalDate.of(1984,8,27), false),
                new DepartmentEmployeeEntity("10523821A", LocalDate.of(1986,9,17), true),
                new DepartmentEmployeeEntity("30523921B", LocalDate.of(1994,10,5), false),
                new DepartmentEmployeeEntity("05523871C", LocalDate.of(1990,12,9), true),
                new DepartmentEmployeeEntity("07523421A", LocalDate.of(1997,11,14), false),
                new DepartmentEmployeeEntity("30523921H", LocalDate.of(2000,1,12), true),
        };
        this.departmentEmployeeRepository.saveAll(Arrays.asList(departmentEmployeeEntities));
        ManagerEntity[] managerRepositories = {
                new ManagerEntity(5, "123123123", "d.jhon@company1.com",
                        Arrays.asList(departmentEmployeeEntities[0], departmentEmployeeEntities[1])),
                new ManagerEntity(6, "32131231", "l.ana@company1.com",
                        Arrays.asList(departmentEmployeeEntities[2], departmentEmployeeEntities[3], departmentEmployeeEntities[5])),
                new ManagerEntity(7, "456456456", "b.ana@company1.com",
                        Arrays.asList(departmentEmployeeEntities[4]))
        };
        this.managerRepository.saveAll(Arrays.asList(managerRepositories));
        DepartmentEntity[] departmentEntities = {
                new DepartmentEntity("Desarrollo", "Departamento de desarrollo", companyEntities[0],
                        Arrays.asList(managerRepositories[1], managerRepositories[2])),
                new DepartmentEntity("Recursos Humanos", "Departamento de recursos humanos", companyEntities[0],
                        Arrays.asList(managerRepositories[0])),
                new DepartmentEntity("Law", "Departamento de leyes", companyEntities[0],
                        Arrays.asList(managerRepositories[2])),
                new DepartmentEntity("QA", "Departamento de seguro de calidad", companyEntities[0],
                        Arrays.asList(managerRepositories[2]))
        };
        this.departmentRepository.saveAll(Arrays.asList(departmentEntities));
    }

    public void deleteAll() {
        this.departmentRepository.deleteAll();
        this.managerRepository.deleteAll();
        this.departmentEmployeeRepository.deleteAll();
        this.companyRepository.deleteAll();
    }
}
