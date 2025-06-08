package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.adapters.rest.cinema.dto.TicketDto;
import es.upm.miw.apaw_practice.adapters.rest.cinema.dto.TicketDtoMapper;
import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;
import es.upm.miw.apaw_practice.domain.services.cinema.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cinema/tickets")
public class TicketResource {

    private final TicketService ticketService;

    @Autowired
    public TicketResource(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<TicketDto> getAll() {
        return ticketService.findAll()
                .stream()
                .map(TicketDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{purchaseDate}")
    public TicketDto getByPurchaseDate(@PathVariable String purchaseDate) {
        Ticket ticket = ticketService.findByPurchaseDate(purchaseDate);
        return TicketDtoMapper.toDto(ticket);
    }

    @PostMapping
    public TicketDto create(@RequestBody TicketDto ticketDto) {
        Ticket ticket = TicketDtoMapper.toDomain(ticketDto);
        return TicketDtoMapper.toDto(ticketService.create(ticket));
    }
}