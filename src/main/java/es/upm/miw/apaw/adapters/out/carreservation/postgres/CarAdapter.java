package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import es.upm.miw.apaw.domain.ports.out.carreservation.CarGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CarAdapter implements CarGateway {
    private final CarRepository carRepository;
}