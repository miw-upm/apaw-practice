package es.upm.miw.apaw_practice.adapters.mongodb.company.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.CompanyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.DepartmentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.ExpenseBillRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.CompanyEntity;
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
            return BigDecimal.ZERO;  // 或者返回其他默认值
        }
        return highestExpenseResult.getHighestAmount();
    }

    // 用于存储聚合结果的内部类
    public static class HighestExpenseResult {
        private BigDecimal highestAmount;

        public BigDecimal getHighestAmount() {
            return highestAmount;
        }

        public void setHighestAmount(BigDecimal highestAmount) {
            this.highestAmount = highestAmount;
        }
    }
}
