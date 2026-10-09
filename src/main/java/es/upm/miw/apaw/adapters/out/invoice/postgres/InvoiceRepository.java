
package es.upm.miw.apaw.adapters.out.invoice.postgres;

import es.upm.miw.apaw.domain.model.invoice.InvoiceFindCriteria;
import es.upm.miw.apaw.domain.model.invoice.LegalServiceInvoiceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface InvoiceRepository extends
        JpaRepository<InvoiceEntity, UUID>,
        JpaSpecificationExecutor<InvoiceEntity> {

    boolean existsByServices_Id(UUID id);

    @Query("""
            select distinct invoice
            from InvoiceEntity invoice
            left join invoice.services service
            where (:paid is null or invoice.paid = :paid)
              and (:issueYear is null or year(invoice.issueDate) = :issueYear)
              and (:serviceName is null or lower(service.name) = lower(:serviceName))
            """)
    List<InvoiceEntity> findByCriteria(
            @Param("paid") Boolean paid,
            @Param("issueYear") Integer issueYear,
            @Param("serviceName") String serviceName
    );

    @Query("""
            select new es.upm.miw.apaw.domain.model.invoice.LegalServiceInvoiceReport(
                service.name,
                count(distinct invoice),
                sum(case when invoice.paid = true then 1 else 0 end)
            )
            from InvoiceEntity invoice
            join invoice.services service
            group by service.name
            order by count(distinct invoice) desc
            """)
    List<LegalServiceInvoiceReport> findLegalServiceInvoiceReport();
}