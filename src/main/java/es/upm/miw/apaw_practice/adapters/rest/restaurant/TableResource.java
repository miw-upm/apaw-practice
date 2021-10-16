package es.upm.miw.apaw_practice.adapters.rest.restaurant;

import es.upm.miw.apaw_practice.domain.models.restaurant.Reserve;
import es.upm.miw.apaw_practice.domain.models.restaurant.Table;
import es.upm.miw.apaw_practice.domain.services.restaurant.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(TableResource.TABLES)
public class TableResource {

    static final String TABLES = "/restaurant/tables";
    static final String ID_NUMBER = "/{number}";
    static final String RESERVES = "/reserves";
    static final String HOLDER = "/holder";

    private TableService tableService;

    @Autowired
    public TableResource(TableService tableService){
        this.tableService = tableService;
    }

    @GetMapping(ID_NUMBER+RESERVES+HOLDER)
    public Stream<Reserve> readHoldersByNumber(@PathVariable Integer number){
        return this.tableService.readHoldersByNumber(number);
    }

    @PutMapping(ID_NUMBER+RESERVES)
    public Table updateNumPeople(@PathVariable Integer number, @RequestBody List<Reserve> reserves){
        return this.tableService.updateNumPeople(number, reserves);
    }

    @PatchMapping
    public void updateStyles(@RequestBody String style){
        this.tableService.updateStyles(style);
    }

}
