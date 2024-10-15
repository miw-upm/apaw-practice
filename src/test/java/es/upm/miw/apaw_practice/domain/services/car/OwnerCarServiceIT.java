package es.upm.miw.apaw_practice.domain.services.car;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.OwnerCarRepository;
import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class OwnerCarServiceIT {

    @Autowired
    OwnerCarService ownerService;
    @Autowired
    OwnerCarRepository ownerRepository;


    @Test
    void testUpdateOwnerCarName() {
        OwnerCar owner = ownerRepository.findByDriverLicense("UCD253").get().toOwnerCar();
        OwnerCar updateOwner = ownerService.updateName(owner.getDriverLicense(), "Manuela");

        assertEquals("Manuela", updateOwner.getName());
        assertEquals("UCD253", updateOwner.getDriverLicense());
    }

    @Test
    void testGetTotalCostByDriverLicense() {
        assertEquals(new BigDecimal(400), ownerService.getTotalCostByDriverLicense("UCD253"));;
    }
}
