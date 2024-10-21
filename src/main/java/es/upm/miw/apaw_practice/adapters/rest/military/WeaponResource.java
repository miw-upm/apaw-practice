package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.services.military.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(WeaponResource.WEAPONS)
public class WeaponResource {
    static final String WEAPONS = "/military/weapons";
    static final String SERIALCODE_ID = "/{serialCode}";
    static final String SEARCH = "/search";

    private final WeaponService weaponService;

    @Autowired
    public WeaponResource(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @DeleteMapping(SERIALCODE_ID)
    public void delete(@PathVariable String serialCode) {
        this.weaponService.delete(serialCode);
    }

    @GetMapping(SEARCH)
    public Stream<String> findDistinctSoldierRanksByManufacturer(@RequestParam String q) {
        String manufacturer = new LexicalAnalyzer().extractWithAssure(q, "manufacturer");
        return this.weaponService.findDistinctSoldierRanksByManufacturer(manufacturer);
    }
}
