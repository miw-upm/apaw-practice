package es.upm.miw.apaw_practice.domain.models.pharmacy;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class PharmacyIT {

    private TreePharmacies pharmacies;
    private TreePharmacies pharmacyOffice;
    private TreePharmacies pharmacyShop;
    private List<Pharmacy> pharmacyList;

    @BeforeEach
    void initializeCompositeTest() {
        pharmacies = new TreePharmaciesComposite("Pharmacy");
        pharmacyList = List.of(
                new Pharmacy("123", "Calle Perdida nº1", 25896, null),
                new Pharmacy("123", "Calle Encontrada nº1", 25896, null)
        );
        pharmacyOffice = new TreePharmaciesComposite("Office");
        pharmacyShop = new TreePharmaciesComposite("Shop");
        List<TreePharmacies> subcategoriesPharmacy = List.of(
                pharmacyOffice,
                pharmacyShop
        );
        subcategoriesPharmacy.forEach(pharmacies::add);
        List<Pharmacy> pharmacyOfficeList = List.of(pharmacyList.get(0));
        List<Pharmacy> pharmacyShopList = List.of(pharmacyList.get(1));
        pharmacyOfficeList.stream()
                .map(TreePharmaciesLeaf::new)
                .forEach(treePharmaciesLeaf -> pharmacyOffice.add(treePharmaciesLeaf));
        pharmacyShopList.stream()
                .map(TreePharmaciesLeaf::new)
                .forEach(treePharmaciesLeaf -> pharmacyShop.add(treePharmaciesLeaf));
    }

    @Test
    void testComposite() {
        assertEquals(List.of(pharmacyList.get(0)), pharmacyOffice.getPharmacies());
        assertEquals(List.of(pharmacyList.get(1)), pharmacyShop.getPharmacies());
    }

}
