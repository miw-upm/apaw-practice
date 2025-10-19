package es.upm.miw.apaw.adapters.resources.metro;

import es.upm.miw.apaw.domain.models.metro.SumTicketPrices;
import es.upm.miw.apaw.domain.models.metro.TrainLine;
import es.upm.miw.apaw.domain.services.metro.TrainLineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TrainLineResource.TRAIN_LINES)
public class TrainLineResource {

    public static final String TRAIN_LINES = "/metro/train-lines";
    public static final String ZONES_TICKET_PRICE = "/zones/ticket-price";
    private final TrainLineService trainLineService;

    @Autowired
    public TrainLineResource(TrainLineService trainLineService) {
        this.trainLineService = trainLineService;
    }

    @PostMapping
    public TrainLine create(@Valid @RequestBody TrainLine trainLine) {
        return this.trainLineService.create(trainLine);
    }

    @GetMapping(ZONES_TICKET_PRICE)
    public SumTicketPrices findTicketPricesSumByLineColor(@RequestParam String color) {
        return this.trainLineService.findTicketPricesSumByLineColor(color);
    }
}
