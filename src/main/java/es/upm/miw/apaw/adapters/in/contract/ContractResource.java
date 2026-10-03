package es.upm.miw.apaw.adapters.in.contract;

import es.upm.miw.apaw.domain.model.contract.Contract;
import es.upm.miw.apaw.domain.model.contract.ContractExpirationReport;
import es.upm.miw.apaw.domain.model.contract.CreationContract;
import es.upm.miw.apaw.domain.services.contract.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ContractResource.CONTRACTS)
@RequiredArgsConstructor
public class ContractResource {
    public static final String CONTRACTS = "/contracts";
    public static final String REPORT = "/report";

    private final ContractService contractService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contract create(@Valid @RequestBody CreationContract creation) {
        return this.contractService.create(creation);
    }

    @GetMapping(REPORT)
    public List<ContractExpirationReport> findExpirationReport() {
        return this.contractService.findExpirationReport();
    }
}
