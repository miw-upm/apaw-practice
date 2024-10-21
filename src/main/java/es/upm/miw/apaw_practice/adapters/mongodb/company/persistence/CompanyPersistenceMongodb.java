package es.upm.miw.apaw_practice.adapters.mongodb.company.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.CompanyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.DepartmentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.ExpenseBillRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.CompanyEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.DepartmentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.ExpenseBillEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.company.Company;
import es.upm.miw.apaw_practice.domain.persistence_ports.company.CompanyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository("CompanyPersistence")
public class CompanyPersistenceMongodb implements CompanyPersistence{

    private final CompanyRepository companyRepository;
    private final ExpenseBillRepository expenseBillRepository;
    private MongoTemplate mongoTemplate;



    @Autowired
    public CompanyPersistenceMongodb(CompanyRepository companyRepository, ExpenseBillRepository expenseBillRepository, MongoTemplate mongoTemplate) {
        this.companyRepository = companyRepository;
        this.expenseBillRepository = expenseBillRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Company findByCompanyname(String companyname) {
        return this.companyRepository.findByCompanyname(companyname)
                .orElseThrow(() -> new NotFoundException("Company name: " + companyname))
                .toCompany();
    }

    @Override
    public void updateIndustry(String companyname, String newIndustry) {
        CompanyEntity companyEntity = this.companyRepository.findByCompanyname(companyname)
                .orElseThrow(() -> new NotFoundException("Company name: " + companyname));
        companyEntity.setIndustry(newIndustry);
        this.companyRepository.save(companyEntity);
    }
    @Override
    public BigDecimal findHighestExpenseAmountByLocation(String location) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.lookup("departmentEntity", "departmentId", "_id", "departmentDetails"),
                Aggregation.match(Criteria.where("departmentDetails.company.location").is(location)),
                Aggregation.group().max("amount").as("highestAmount")
        );

        AggregationResults<HighestExpenseResult> results = mongoTemplate.aggregate(aggregation, "expenseBillEntity", HighestExpenseResult.class);
        HighestExpenseResult highestExpenseResult = results.getUniqueMappedResult();
        if (highestExpenseResult == null || highestExpenseResult.getHighestAmount() == null) {
            return BigDecimal.ZERO;
        }
        return highestExpenseResult.getHighestAmount();
    }


    public static class HighestExpenseResult {
        private BigDecimal highestAmount;

        public BigDecimal getHighestAmount() {
            return highestAmount;
        }

        public void setHighestAmount(BigDecimal highestAmount) {
            this.highestAmount = highestAmount;
        }
    }

    @Override
    public List<String> findManagementNamesByIndustryAndDescription(String industry, String description) {
        // 1. 从 `ExpenseBillEntity` 中查找符合 description 的账单
        List<ExpenseBillEntity> expenseBills = this.expenseBillRepository.findByDescription(description);

        // 2. 提取所有关联的 `DepartmentEntity`
        List<DepartmentEntity> departments = expenseBills.stream()
                .flatMap(expenseBill -> expenseBill.getDepartments().stream())
                .collect(Collectors.toList());

        // 3. 获取符合 `industry` 的公司
        List<CompanyEntity> companies = this.companyRepository.findByIndustry(industry);

        // 4. 通过公司过滤部门，并返回管理者名称列表
        return departments.stream()
                .filter(department -> companies.stream()
                        .anyMatch(company -> company.getDepartmentEntities().contains(department)))
                .map(department -> department.getManagementEntity().getName())
                .distinct()
                .collect(Collectors.toList());
    }

}
