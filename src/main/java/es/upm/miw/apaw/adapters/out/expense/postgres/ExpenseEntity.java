package es.upm.miw.apaw.adapters.out.expense.postgres;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expense")
public class ExpenseEntity {
    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String reference;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String description;

    private LocalDate expenseDate;
    private String category;
    private Boolean isPaid;

    // 显式配置 fetch = FetchType.LAZY 遵循老师的硬性要求
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierEntity;

    // UserSnapshot 属于跨服务微服务数据，在数据库本地只存 userId (UUID)
    private UUID userId;
}