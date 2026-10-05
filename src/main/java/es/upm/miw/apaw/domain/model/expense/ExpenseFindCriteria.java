package es.upm.miw.apaw.domain.model.expense;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseFindCriteria {

    private String category;

    private Boolean unpaid;

    private String supplierTaxId;

    private String userMobile;

    public boolean isAll() {
        return !this.hasCategory() && !this.hasUnpaid()
                && !this.hasSupplierTaxId() && !this.hasUserMobile();
    }

    public boolean hasCategory() {
        return this.category != null && !this.category.isBlank();
    }

    public boolean hasUnpaid() {
        return this.unpaid != null;
    }

    public boolean hasSupplierTaxId() {
        return this.supplierTaxId != null && !this.supplierTaxId.isBlank();
    }

    public boolean hasUserMobile() {
        return this.userMobile != null && !this.userMobile.isBlank();
    }
}