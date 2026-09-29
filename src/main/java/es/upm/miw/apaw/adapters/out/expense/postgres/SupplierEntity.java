package es.upm.miw.apaw.adapters.out.expense.postgres;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")
public class SupplierEntity {
    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String taxId;

    @Column(nullable = false)
    private String companyName;

    private String address;
    private String contactEmail;
    private String corporatePhone;
    private Integer paymentTermsDays;
}