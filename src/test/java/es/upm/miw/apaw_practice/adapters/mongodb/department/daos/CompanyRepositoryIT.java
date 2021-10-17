package es.upm.miw.apaw_practice.adapters.mongodb.department.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class CompanyRepositoryIT {

    @Autowired
    private CompanyRepository companyRepository;
}
