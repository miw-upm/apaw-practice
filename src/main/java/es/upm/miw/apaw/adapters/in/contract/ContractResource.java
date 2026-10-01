package es.upm.miw.apaw.adapters.in.contract;

import es.upm.miw.apaw.domain.model.contract.Contract;
import es.upm.miw.apaw.domain.model.contract.CreationContract;
import es.upm.miw.apaw.domain.services.contract.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ContractResource.CONTRACTS)
@RequiredArgsConstructor
public class ContractResource {
    public static final String CONTRACTS = "/contracts";

    private final ContractService contractService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contract create(@Valid @RequestBody CreationContract creation) {
        return this.contractService.create(creation);
    }
}
