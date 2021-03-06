package es.upm.miw.apaw_practice.domain.services.factory;

import es.upm.miw.apaw_practice.domain.models.factory.Machine;
import es.upm.miw.apaw_practice.domain.persistence_ports.factory.MachinePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class MachineService {
    private MachinePersistence machinePersistence;

    @Autowired
    public MachineService(MachinePersistence machinePersistence) {
        this.machinePersistence = machinePersistence;
    }

    public Machine updateStatus(String id, Boolean active) {
        return this.machinePersistence.updateStatus(id, active);
    }

    public Stream<Machine> findMachineByEmployeeDegreeTitle(String title) {
        return this.machinePersistence.findMachineByEmployeeDegreeTitle(title);
    }
}
