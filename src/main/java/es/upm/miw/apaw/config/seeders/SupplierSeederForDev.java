package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.expense.postgres.SupplierEntity;
import es.upm.miw.apaw.adapters.out.expense.postgres.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Profile({"dev", "test"})
public class SupplierSeederForDev implements ApplicationRunner {

    public static final UUID SUPPLIER_1_ID = UUID.fromString("aaaa1111-1111-1111-1111-111111111111");
    public static final UUID SUPPLIER_2_ID = UUID.fromString("aaaa2222-2222-2222-2222-222222222222");

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierSeederForDev(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        this.seed();
    }

    public void seed() {
        SupplierEntity s1 = SupplierEntity.builder()
                .id(SUPPLIER_1_ID)
                .taxId("B12345678")
                .companyName("Tech Legal Services S.L.")
                .address("Calle Gran Via 12, Madrid")
                .contactEmail("contact@techlegal.com")
                .corporatePhone("+34910000001")
                .paymentTermsDays(30)
                .build();

        SupplierEntity s2 = SupplierEntity.builder()
                .id(SUPPLIER_2_ID)
                .taxId("A87654321")
                .companyName("Office Supplies Corp")
                .address("Av. Complutense 4, Madrid")
                .contactEmail("info@officesupplies.es")
                .corporatePhone("+34910000002")
                .paymentTermsDays(15)
                .build();

        this.supplierRepository.saveAll(List.of(s1, s2));
    }
}