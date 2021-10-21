package es.upm.miw.apaw_practice.domain.services.car_hire;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_hire.Booking;
import es.upm.miw.apaw_practice.domain.models.car_hire.Model;
import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.BookingPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.ModelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookingService {

    private final BookingPersistence bookingPersistence;
    private final ModelPersistence modelPersistence;

    @Autowired
    public BookingService(BookingPersistence bookingPersistence, ModelPersistence modelPersistence) {
        this.bookingPersistence = bookingPersistence;
        this.modelPersistence = modelPersistence;
    }

    public void delete(String bookingNumber) {
        this.bookingPersistence.delete(bookingNumber);
    }

    public Stream<Booking> readAll() {
        return this.bookingPersistence.readAll();
    }

    public Stream<Booking> readByRenterName(String name) {
        return this.bookingPersistence.readByRenterName(name);
    }

    public Stream<Vehicle> getVehiclesVinNumberByRentersName(String name) {
        Stream<Booking> bookings = this.readByRenterName(name);
        return bookings.flatMap(vehicle ->
                vehicle.getVehicleList().stream());
    }

    public Stream<Model> readAllModels() {
        return this.modelPersistence.readAll();
    }

    public boolean assertExistModelType(String type) {
        return this.readAllModels().anyMatch(model -> model.getType().equals(type));
    }

    public Set<String> getRentersNameDistinct(Supplier<Stream<Vehicle>> vehicles) {
        return this.readAll()
                .filter(booking -> booking.getVehicleList().stream()
                        .anyMatch(vehicle -> vehicles.get()
                                .anyMatch(vehicleInLoopOfVehicles -> vehicleInLoopOfVehicles.getVinNumber().equals(vehicle.getVinNumber()))
                        )
                )
                .map(Booking::getRenter)
                .map(Renter::getName)
                .collect(Collectors.toSet());
    }

    public Set<Renter> getRentersNameByModelType(String type) {
        if (!this.assertExistModelType(type)) {
            throw new NotFoundException("Model type: " + type);
        } else {
            Supplier<Stream<Vehicle>> vehicles =
                    (() -> this.readAllModels()
                            .filter(model -> model.getType().equals(type))
                            .flatMap(model -> model.getVehicleList().stream())
                    );
            Set<String> rentersName = this.getRentersNameDistinct(vehicles);
            Set<Renter> renters = new LinkedHashSet<>();
            for (String name : rentersName) {
                Renter renter = new Renter();
                renter.setName(name);
                renters.add(renter);
            }
            return renters;
        }
    }
}
