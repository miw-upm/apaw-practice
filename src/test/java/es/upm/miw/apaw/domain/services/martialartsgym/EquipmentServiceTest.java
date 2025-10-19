package es.upm.miw.apaw.domain.services.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Equipment;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.EquipmentPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import java.util.List;
import java.math.BigDecimal;

@SpringBootTest
@ActiveProfiles("test")
class EquipmentServiceTest {

    @MockitoBean
    private EquipmentPersistence equipmentPersistence;

    @Autowired
    private EquipmentService equipmentService;

    @Test
    void testUpdateUnitCost() {
        // Given
        Integer barCode = 1005;
        Equipment equipment = Equipment.builder()
                .barCode(barCode)
                .itemLabel("Kicking Pad")
                .unitCost(new BigDecimal("40.00"))
                .build();


        BDDMockito.given(equipmentPersistence.updateUnitCost(barCode, new BigDecimal("45.00")))
                .willReturn(Equipment.builder()
                        .barCode(barCode)
                        .itemLabel("Kicking Pad")
                        .unitCost(new BigDecimal("45.00"))
                        .build());

        // When
        Equipment updated = equipmentService.updateUnitCost(barCode, new BigDecimal("45.00"));

        // Then
        Assertions.assertNotNull(updated);
        Assertions.assertEquals(barCode, updated.getBarCode());
        Assertions.assertEquals(new BigDecimal("45.00"), updated.getUnitCost());
        Assertions.assertEquals("Kicking Pad", updated.getItemLabel());
    }
    @Test
    void testFullUpdateEquipment() {


        Equipment updated = Equipment.builder()
                .barCode(2001)
                .itemLabel("Professional Gloves")
                .unitCost(new BigDecimal("60.00"))
                .build();

        BDDMockito.given(equipmentPersistence.updateEquipment(updated))
                .willReturn(updated);

        // When
        Equipment result = equipmentService.updateEquipment(updated);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Professional Gloves", result.getItemLabel());
        Assertions.assertEquals(new BigDecimal("60.00"), result.getUnitCost());
    }
    @Test
    void testFindMobilesByItemLabel() {
        String itemLabel = "Karate Belt";
        List<String> expectedMobiles = List.of("600123123", "699888777");

        BDDMockito.given(equipmentPersistence.findMobilesByItemLabel(itemLabel))
                .willReturn(expectedMobiles);

        List<String> result = equipmentService.findMobilesByItemLabel(itemLabel);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains("600123123"));
        Assertions.assertTrue(result.contains("699888777"));
    }


}
