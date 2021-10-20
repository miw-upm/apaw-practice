package es.upm.miw.apaw_practice.domain.models.restaurant;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class TableTest {

    private TreeTable treeTable;

    @Test
    void testAddAndTotalPrice(){
        Table[] tables = {
                new Table(11,true,"classic",new BigDecimal(10), List.of()),
                new Table(12,true,"classic",new BigDecimal(20), List.of()),
                new Table(13,true,"classic",new BigDecimal(30), List.of())
        };
        TreeTableLeaf treeTableLeaf = new TreeTableLeaf(tables[0]);
        treeTable = treeTableLeaf;
        assertEquals(new BigDecimal(10),treeTable.totalPrice());

        TreeTableComposite treeTableComposite = new TreeTableComposite();
        treeTableComposite.add(new TreeTableLeaf(tables[1]));
        treeTableComposite.add(new TreeTableLeaf(tables[2]));
        treeTable = treeTableComposite;
        assertEquals(new BigDecimal(50),treeTable.totalPrice());

        treeTableComposite.add(treeTableLeaf);
        assertEquals(new BigDecimal(60),treeTable.totalPrice());
    }
}
