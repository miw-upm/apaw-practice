package es.upm.miw.apaw_practice.domain.services.car;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.OwnerCarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.PieceRepository;
import es.upm.miw.apaw_practice.domain.models.car.Car;
import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import es.upm.miw.apaw_practice.domain.models.car.Piece;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class CarServiceIT {

    @Autowired
    CarService carService;

    @Autowired
    PieceRepository pieceRepository;

    @Autowired
    OwnerCarRepository ownerCarRepository;

    @Test
    void testCreateCar() {
        Piece piece1 = pieceRepository.findByPartNumber("WSDF").get().toPiece();
        Piece piece2 = pieceRepository.findByPartNumber("SFGE").get().toPiece();
        List<Piece> pieceList = new ArrayList<>();
        pieceList.add(piece1);
        pieceList.add(piece2);
        OwnerCar owner = ownerCarRepository.findByDriverLicense("YYSG34").get().toOwnerCar();
        Car car = new Car("Toyota", true, new BigDecimal(38000.5), owner, pieceList );

        Car createdCar = this.carService.create(car);
        assertNotNull(createdCar);
    }
}
