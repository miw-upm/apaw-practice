package es.upm.miw.apaw.adapters.out.invoice.postgres;
import es.upm.miw.apaw.domain.model.invoice.LegalServiceInvoiceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, UUID> {

    boolean existsByServices_Id(UUID id);

    @Query(""" 
            select new es.upm.miw.apaw.domain.model.invoice.LegalServiceInvoiceReport( 
            service.name, count(distinct invoice), sum(case when invoice.paid = true then 1 else 0 end) )
            from InvoiceEntity invoice join invoice.services service 
            group by service.name 
            order by count(distinct invoice) desc 
            """)
    List<LegalServiceInvoiceReport> findLegalServiceInvoiceReport();
}