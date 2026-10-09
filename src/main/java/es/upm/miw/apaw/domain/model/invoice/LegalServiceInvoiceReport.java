package es.upm.miw.apaw.domain.model.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegalServiceInvoiceReport {
    private String serviceName;
    private long totalInvoiceCount;
    private long paidInvoiceCount;
}