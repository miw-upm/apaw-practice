package es.upm.miw.apaw.adapters.out.expense.postgres;

import es.upm.miw.apaw.domain.model.expense.Supplier;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "supplier")
public class SupplierEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(unique = true, nullable = false)
    private String taxId;

    @Column(nullable = false)
    private String companyName;

    private String address;
    private String contactEmail;
    private String corporatePhone;
    private Integer paymentTermsDays;

    public SupplierEntity(Supplier supplier) {
        BeanUtils.copyProperties(supplier, this);
    }

    public Supplier toDomain() {
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(this, supplier);
        return supplier;
    }
}