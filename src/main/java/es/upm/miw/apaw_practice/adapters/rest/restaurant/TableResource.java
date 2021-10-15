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
    static final String ID = "/{id}";
    static final String RESERVES = "/reserves";
    static final String HOLDER = "/holder";

    private TableService tableService;

    @Autowired
    public TableResource(TableService tableService){
        this.tableService = tableService;
    }

    @GetMapping(ID+RESERVES+HOLDER)
    public Stream<Reserve> readHoldersByNumber(@PathVariable Integer id){
        return this.tableService.readHoldersByNumber(id);
    }

    @PutMapping(ID+RESERVES)
    public Table updateNumPeople(@PathVariable Integer id, @RequestBody List<Reserve> reserves){
        return this.tableService.updateNumPeople(id, reserves);
    }

    @PatchMapping
    public void updateStyles(@RequestBody String style){
        this.tableService.updateStyles(style);
    }

}
