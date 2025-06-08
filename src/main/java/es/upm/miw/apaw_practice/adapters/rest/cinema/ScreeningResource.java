package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.adapters.rest.cinema.dto.ScreeningDto;
import es.upm.miw.apaw_practice.adapters.rest.cinema.dto.ScreeningDtoMapper;
import es.upm.miw.apaw_practice.domain.services.cinema.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cinema/screenings")
public class ScreeningResource {

    private final ScreeningService screeningService;

    @Autowired
    public ScreeningResource(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping
    public List<ScreeningDto> getAll() {
        return screeningService.findAll()
                .stream()
                .map(ScreeningDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{screeningTime}")
    public ScreeningDto getByScreeningTime(@PathVariable String screeningTime) {
        return ScreeningDtoMapper.toDto(screeningService.findByScreeningTime(screeningTime));
    }

    @PostMapping
    public ScreeningDto create(@RequestBody ScreeningDto screeningDto) {
        return ScreeningDtoMapper.toDto(
                screeningService.create(ScreeningDtoMapper.toDomain(screeningDto))
        );
    }
}