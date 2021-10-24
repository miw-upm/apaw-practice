package es.upm.miw.apaw_practice.adapters.rest.tv_series;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.tv_series.Producer;
import es.upm.miw.apaw_practice.domain.services.tv_series.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(ProducerResource.PRODUCERS)
public class ProducerResource {

    static final String PRODUCERS = "/producers";
    static final String BUSINESS_NAME = "/{businessName}";
    static final String SEARCH = "/search";

    private final ProducerService producerService;

    @Autowired
    public ProducerResource(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PutMapping(BUSINESS_NAME)
    public void update(@PathVariable String businessName, @RequestBody Producer producer) {
        this.producerService.update(businessName,producer);
    }

    @GetMapping(SEARCH)
    public Stream<Long> findProducerPhonesByTvSeriesYearAndPlayerNationality(@RequestParam String q) {
        int year = Integer.parseInt(new LexicalAnalyzer().extractWithAssure(q,"year"));
        String nationality = new LexicalAnalyzer().extractWithAssure(q,"nationality");
        return this.producerService.findProducerPhonesByTvSeriesYearAndPlayerNationality(year,nationality);
    }
}