package es.upm.miw.apaw.adapters.in.evidencemanagement;

import es.upm.miw.apaw.domain.model.evidencemanagement.CustodianActivityReport;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.services.evidencemanagement.CustodyRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(CustodyRecordResource.CUSTODY_RECORDS)
@RequiredArgsConstructor
public class CustodyRecordResource {
    public static final String CUSTODY_RECORDS = "/custody-records";
    public static final String ID = "/{id}";
    public static final String REPORT = "/report";

    private final CustodyRecordService custodyRecordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustodyRecord create(@Valid @RequestBody CustodyRecordDto custodyRecordDto) {
        return this.custodyRecordService.create(custodyRecordDto.toDomain());
    }

    @GetMapping
    public List<CustodyRecord> findAll() {
        return this.custodyRecordService.findAll();
    }

    @GetMapping(ID)
    public CustodyRecord read(@PathVariable UUID id) {
        return this.custodyRecordService.read(id);
    }

    @PutMapping(ID)
    public CustodyRecord update(@PathVariable UUID id, @Valid @RequestBody CustodyRecordDto custodyRecordDto) {
        return this.custodyRecordService.update(id, custodyRecordDto.toDomain());
    }

    @PatchMapping(ID)
    public CustodyRecord patch(@PathVariable UUID id, @Valid @RequestBody CustodyRecordPatchDto custodyRecordPatchDto) {
        return this.custodyRecordService.patch(id, custodyRecordPatchDto.toDomain());
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.custodyRecordService.delete(id);
    }

    @GetMapping(REPORT)
    public List<CustodianActivityReport> findActivityReport() {
        return this.custodyRecordService.findActivityReport();
    }
}
