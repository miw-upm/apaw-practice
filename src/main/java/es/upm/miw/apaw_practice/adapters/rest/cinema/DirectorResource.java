package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.adapters.rest.cinema.dto.DirectorDto;
import es.upm.miw.apaw_practice.adapters.rest.cinema.dto.DirectorDtoMapper;
import es.upm.miw.apaw_practice.domain.services.cinema.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cinema/directors")
public class DirectorResource {

    private final DirectorService directorService;

    @Autowired
    public DirectorResource(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public List<DirectorDto> getAll() {
        return directorService.findAll()
                .stream()
                .map(DirectorDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{dni}")
    public DirectorDto getByDni(@PathVariable String dni) {
        return DirectorDtoMapper.toDto(directorService.findByDni(dni));
    }

    @PostMapping
    public DirectorDto create(@RequestBody DirectorDto directorDto) {
        return DirectorDtoMapper.toDto(
                directorService.create(DirectorDtoMapper.toDomain(directorDto))
        );
    }
}