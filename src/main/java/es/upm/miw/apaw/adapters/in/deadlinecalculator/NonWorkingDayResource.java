package es.upm.miw.apaw.adapters.in.deadlinecalculator;

import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;
import es.upm.miw.apaw.domain.services.deadlinecalculator.NonWorkingDayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(NonWorkingDayResource.NON_WORKING_DAYS)
@RequiredArgsConstructor
public class NonWorkingDayResource {
    public static final String NON_WORKING_DAYS = "/non-working-days";

    private final NonWorkingDayService nonWorkingDayService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NonWorkingDay create(@Valid @RequestBody NonWorkingDay nonWorkingDay) {
        return this.nonWorkingDayService.create(nonWorkingDay);
    }
}
