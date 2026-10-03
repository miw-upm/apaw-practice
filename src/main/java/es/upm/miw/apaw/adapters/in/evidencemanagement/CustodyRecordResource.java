package es.upm.miw.apaw.adapters.in.evidencemanagement;

import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.services.evidencemanagement.CustodyRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(CustodyRecordResource.CUSTODY_RECORDS)
@RequiredArgsConstructor
public class CustodyRecordResource {
    public static final String CUSTODY_RECORDS = "/custody-records";
    public static final String ID = "/{id}";

    private final CustodyRecordService custodyRecordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustodyRecord create(@Valid @RequestBody CustodyRecord custodyRecord) {
        return this.custodyRecordService.create(custodyRecord);
    }

    @GetMapping(ID)
    public CustodyRecord read(@PathVariable UUID id) {
        return this.custodyRecordService.read(id);
    }
}
