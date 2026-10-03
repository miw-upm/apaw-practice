package es.upm.miw.apaw.functionaltests.carreservation;

import es.upm.miw.apaw.adapters.in.carreservation.CarResource;
import es.upm.miw.apaw.domain.model.carreservation.Car;
import es.upm.miw.apaw.domain.model.carreservation.CarSeatsAndFuelUpdate;
import es.upm.miw.apaw.domain.model.carreservation.FuelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.CarReservationSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CarResourceFT {

    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testRead() {
        this.restTestClient.get().uri(CarResource.CARS + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Car.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(CAR_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(CarResource.CARS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get().uri(CarResource.CARS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Car[].class)
                .value(body -> assertThat(body).extracting(Car::getId)
                        .contains(ID_0, ID_1, ID_2, ID_3, ID_4));
    }

    @Test
    void testCreateDefaultsToFiveSeats() {
        Car car = Car.builder()
                .brand("Peugeot")
                .model("308")
                .licensePlate("LP-" + UUID.randomUUID().toString().substring(0, 5))
                .registrationDate(LocalDate.now())
                .build();
        car.doDefault();

        this.restTestClient.post().uri(CarResource.CARS)
                .body(car)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Car.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getNumberOfSeats()).isEqualTo(5);
                });
    }

    @Test
    void testCreateBlankLicensePlate() {
        Car car = Car.builder()
                .brand("Peugeot")
                .model("308")
                .licensePlate(" ")
                .registrationDate(LocalDate.now())
                .build();

        this.restTestClient.post().uri(CarResource.CARS)
                .body(car)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateDuplicateLicensePlate() {
        Car car = Car.builder()
                .brand("Peugeot")
                .model("308")
                .licensePlate(CAR_0.getLicensePlate())
                .registrationDate(LocalDate.now())
                .build();

        this.restTestClient.post().uri(CarResource.CARS)
                .body(car)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate() {
        Car car = this.createCar();
        Car updatedInfo = Car.builder()
                .brand(car.getBrand())
                .model("New Model")
                .licensePlate(car.getLicensePlate())
                .registrationDate(car.getRegistrationDate())
                .numberOfSeats(2)
                .fuelType(FuelType.ELECTRIC)
                .build();

        this.restTestClient.put().uri(CarResource.CARS + "/" + car.getId())
                .body(updatedInfo)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Car.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(car.getId());
                    assertThat(body.getModel()).isEqualTo("New Model");
                    assertThat(body.getNumberOfSeats()).isEqualTo(2);
                });
    }

    @Test
    void testUpdateNotFound() {
        Car car = Car.builder()
                .brand("Missing")
                .model("Missing")
                .licensePlate("9999ZZZ")
                .registrationDate(LocalDate.now())
                .build();

        this.restTestClient.put().uri(CarResource.CARS + "/" + UUID.randomUUID())
                .body(car)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDuplicateLicensePlate() {
        Car car = this.createCar();
        Car duplicateInfo = Car.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .licensePlate(CAR_0.getLicensePlate())
                .registrationDate(car.getRegistrationDate())
                .build();

        this.restTestClient.put().uri(CarResource.CARS + "/" + car.getId())
                .body(duplicateInfo)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testDelete() {
        Car car = this.createCar();
        this.restTestClient.delete().uri(CarResource.CARS + "/" + car.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();

        this.restTestClient.get().uri(CarResource.CARS + "/" + car.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchSeatsAndFuel() {
        Car car = this.createCar();
        CarSeatsAndFuelUpdate update = new CarSeatsAndFuelUpdate(7, FuelType.ELECTRIC);

        this.restTestClient.patch().uri(CarResource.CARS + "/" + car.getId())
                .body(update)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Car.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(car.getId());
                    assertThat(body.getNumberOfSeats()).isEqualTo(7);
                    assertThat(body.getFuelType()).isEqualTo(FuelType.ELECTRIC);
                    assertThat(body.getBrand()).isEqualTo(car.getBrand());
                });
    }

    @Test
    void testPatchNotFound() {
        UUID missingId = UUID.randomUUID();
        CarSeatsAndFuelUpdate update = new CarSeatsAndFuelUpdate(2, FuelType.HYBRID);

        this.restTestClient.patch().uri(CarResource.CARS + "/" + missingId)
                .body(update)
                .exchange()
                .expectStatus().isNotFound();
    }

    private Car createCar() {
        Car car = Car.builder()
                .brand("FT Brand")
                .model("FT Model")
                .licensePlate("FT-" + UUID.randomUUID().toString().substring(0, 5))
                .registrationDate(LocalDate.now())
                .build();
        car.doDefault();

        return this.restTestClient.post().uri(CarResource.CARS)
                .body(car)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Car.class)
                .returnResult().getResponseBody();
    }
}