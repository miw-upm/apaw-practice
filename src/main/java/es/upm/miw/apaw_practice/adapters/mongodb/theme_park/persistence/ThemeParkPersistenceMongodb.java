package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos.OperatorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos.RideRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos.ThemeParkRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.OperatorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.RideEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.ThemeParkEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.theme_park.ThemePark;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.ThemeParkPersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Repository("themeParkPersistence")
public class ThemeParkPersistenceMongodb implements ThemeParkPersistence {

    private final ThemeParkRepository themeParkRepository;
    private final OperatorRepository operatorRepository;
    private final RideRepository rideRepository;
    @Autowired
    public ThemeParkPersistenceMongodb(ThemeParkRepository themeParkRepository, OperatorRepository operatorRepository, RideRepository rideRepository) {
        this.themeParkRepository = themeParkRepository;
        this.operatorRepository = operatorRepository;
        this.rideRepository = rideRepository;
    }

    @Override
    public Stream<ThemePark> readAll(){
        return this.themeParkRepository.findAll().stream()
                .map(ThemeParkEntity::toThemePark);
    }

    @Override
    public ThemePark readById(String id) {
        return this.themeParkRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Theme Park with id " + id + " not found"))
                .toThemePark();
    }

    @Override
    public ThemePark updateThemePark(ThemePark themePark) {
        ThemeParkEntity themeParkEntity = this.themeParkRepository
                .findById(themePark.getId())
                .orElseThrow(() -> new NotFoundException("Theme Park with id " + themePark.getId() + " not found"));

        BeanUtils.copyProperties(themePark, themeParkEntity, "id");
        return this.themeParkRepository.save(themeParkEntity)
                .toThemePark();
    }

    @Override
    public BigDecimal getSumPriceByNick(String nick){
        List<RideEntity> rides = this.operatorRepository.findAll()
                                .stream()
                                .filter(operatorEntity -> operatorEntity.getNick().equals(nick))
                                .map(OperatorEntity::getRideEntity)
                                .toList();

        return this.themeParkRepository.findAll()
                .stream()
                .filter(themePark -> themePark.getRideEntities()
                        .stream()
                        .anyMatch(rides::contains))
                .map(ThemeParkEntity::toThemePark)
                .distinct()
                .map(ThemePark::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<String> getThemeParkIdsByEntranceDate(LocalDateTime entranceDate){
        List <RideEntity> ridesWithUserEntranceDate = this.rideRepository.findAll().stream()
                .filter(ride -> ride.getUserEntities().stream()
                        .anyMatch(user -> user.getEntranceDate().equals(entranceDate)))
                .toList();
        return this.themeParkRepository.findAll()
                .stream()
                .filter(themePark -> themePark.getRideEntities()
                        .stream()
                        .anyMatch(ridesWithUserEntranceDate::contains))
                .filter(themePark -> themePark.getCreationDate().isAfter(entranceDate))
                .map(ThemeParkEntity::getId)
                .distinct()
                .toList();
    }
}
