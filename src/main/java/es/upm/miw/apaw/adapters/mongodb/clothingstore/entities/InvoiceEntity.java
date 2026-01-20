package es.upm.miw.apaw.adapters.mongodb.clothingstore.entities;

import es.upm.miw.apaw.domain.models.clothingstore.Invoice;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "invoices")
public class InvoiceEntity {

    @Id
    @EqualsAndHashCode.Include
    private String number;
    private LocalDate issuedAt;
    private BigDecimal tax;
    private LocalDate dueDate;

    public InvoiceEntity(Invoice invoice) {
        BeanUtils.copyProperties(invoice, this);
    }

    public Invoice toInvoice() {
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(this, invoice);
        return invoice;
    }

    public void fromInvoice(Invoice invoice) {
        BeanUtils.copyProperties(invoice, this);
    }
}

