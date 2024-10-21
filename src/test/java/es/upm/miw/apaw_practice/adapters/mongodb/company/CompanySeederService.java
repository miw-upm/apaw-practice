package es.upm.miw.apaw_practice.adapters.mongodb.company;


import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.*;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.*;


import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CompanySeederService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ExpenseBillRepository expenseBillRepository;
    @Autowired
    private ManagementRepository managementRepository;

    @PostConstruct
    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Company Initial Load -----------");

        ManagementEntity[] managements = {
                new ManagementEntity("John Doe", true),
                new ManagementEntity("Bob Smith", false),
                new ManagementEntity( "Carol White", true),
                new ManagementEntity("David Brown", false)
        };
        this.managementRepository.saveAll(Arrays.asList(managements));

        DepartmentEntity[] departments = {
                new DepartmentEntity("Research and Development", new BigDecimal("100000.0"), 50, managements[0]),
                new DepartmentEntity("IT Support", new BigDecimal("50000.0"), 30, managements[0]),
                new DepartmentEntity("Customer Service", new BigDecimal("75000.0"), 40, managements[1]),
                new DepartmentEntity("Compliance", new BigDecimal("60000.0"), 20, managements[1]),
                new DepartmentEntity("Marketing", new BigDecimal("90000.0"), 25, managements[2])
        };
        this.departmentRepository.saveAll(Arrays.asList(departments));

        CompanyEntity[] companies = {
                new CompanyEntity("TechCorp", "New York", "Technology", LocalDate.of(2005, 4, 20),List.of(departments[1])),
                new CompanyEntity("FinServe", "London", "Finance", LocalDate.of(2010, 8, 15),List.of(departments[0])),
                new CompanyEntity("HealthPlus", "New York", "Healthcare", LocalDate.of(2018, 3, 10),List.of(departments[2]))
        };
        this.companyRepository.saveAll(Arrays.asList(companies));

        ExpenseBillEntity[] expenseBills = {
                new ExpenseBillEntity( "Office Supplies", new BigDecimal("500.0"), LocalDateTime.of(2023, 2, 15, 10, 0), List.of(departments[1])),
                new ExpenseBillEntity("Travel Expenses", new BigDecimal("1200.0"), LocalDateTime.of(2023, 6, 10, 15, 30), List.of(departments[0])),
                new ExpenseBillEntity("IT Upgrade", new BigDecimal("15000.0"), LocalDateTime.of(2023, 8, 5, 10, 0), List.of(departments[2]))
        };
        this.expenseBillRepository.saveAll(Arrays.asList(expenseBills));
    }

    public void deleteAll() {
        this.expenseBillRepository.deleteAll();
        this.departmentRepository.deleteAll();
        this.companyRepository.deleteAll();
        this.managementRepository.deleteAll();
    }
}



