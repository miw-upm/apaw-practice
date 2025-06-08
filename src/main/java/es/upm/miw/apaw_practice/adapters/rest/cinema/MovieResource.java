package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.adapters.rest.cinema.dto.MovieDto;
import es.upm.miw.apaw_practice.adapters.rest.cinema.dto.MovieDtoMapper;
import es.upm.miw.apaw_practice.domain.services.cinema.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cinema/movies")
public class MovieResource {

    private final MovieService movieService;

    @Autowired
    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getAll() {
        return movieService.findAll()
                .stream()
                .map(MovieDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{title}")
    public MovieDto getByTitle(@PathVariable String title) {
        return MovieDtoMapper.toDto(movieService.findByTitle(title));
    }

    @PostMapping
    public MovieDto create(@RequestBody MovieDto movieDto) {
        return MovieDtoMapper.toDto(
                movieService.create(MovieDtoMapper.toDomain(movieDto))
        );
    }
    @PutMapping("/{title}")
    public MovieDto update(@PathVariable String title, @RequestBody MovieDto movieDto) {
        return MovieDtoMapper.toDto(
                movieService.update(title, MovieDtoMapper.toDomain(movieDto))
        );
    }

    @DeleteMapping("/{title}")
    public void delete(@PathVariable String title) {
        movieService.delete(title);
    }
}