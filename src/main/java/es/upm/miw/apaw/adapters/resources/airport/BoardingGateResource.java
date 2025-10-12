package es.upm.miw.apaw.adapters.resources.airport;

import es.upm.miw.apaw.adapters.resources.shop.ShoppingCartResource;
import es.upm.miw.apaw.domain.models.airport.BoardingGate;
import es.upm.miw.apaw.domain.services.airport.BoardingGateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(BoardingGateResource.BOARDING_GATES)
public class BoardingGateResource {
    public static final String BOARDING_GATES = "/airport/boarding-gates";

    public static final String ID_ID = "/{id}";
    public static final String OPENED = "/opened";

    private final BoardingGateService boardingGateService;

    @Autowired
    public BoardingGateResource(BoardingGateService boardingGateService) {
        this.boardingGateService = boardingGateService;
    }

    @PutMapping(ID_ID + OPENED)
    public BoardingGate updateOpened(@Valid @PathVariable UUID id, @RequestBody Boolean opened) {
        return this.boardingGateService.updateOpened(id, opened);
    }
}
