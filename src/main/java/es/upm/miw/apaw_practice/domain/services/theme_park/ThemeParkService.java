package es.upm.miw.apaw_practice.domain.services.theme_park;

import es.upm.miw.apaw_practice.domain.models.theme_park.ThemePark;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.ThemeParkPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ThemeParkService {

    private final ThemeParkPersistence themeParkPersistence;

    @Autowired
    public ThemeParkService(ThemeParkPersistence themeParkPersistence) {
        this.themeParkPersistence = themeParkPersistence;
    }

    public ThemePark updateParkStatus(String id) {
        ThemePark themePark = this.themeParkPersistence.readById(id);
        themePark.setOpened(true);
        return this.themeParkPersistence.updateThemePark(themePark);
    }

}
