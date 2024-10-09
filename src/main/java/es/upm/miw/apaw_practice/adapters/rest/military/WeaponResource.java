package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.domain.services.military.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(WeaponResource.WEAPONS)
public class WeaponResource {
    static final String WEAPONS = "/military/weapons";
    static final String SERIALCODE_ID = "/{serialCode}";

    private final WeaponService weaponService;

    @Autowired
    public WeaponResource(WeaponService weaponService) { this.weaponService = weaponService; }

    @DeleteMapping(SERIALCODE_ID)
    public void delete(@PathVariable String serialCode) { this.weaponService.delete(serialCode); }
}
