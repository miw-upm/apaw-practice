package es.upm.miw.apaw.adapters.mongodb.clothingstore.entities;

import es.upm.miw.apaw.domain.models.clothingstore.Invoice;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceEntity {

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

