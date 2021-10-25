package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.tv_series.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ProducerPersistenceMongodbIT {

    @Autowired
    private ProducerPersistenceMongodb producerPersistence;

    @Test
    void testRead() {
        Producer producerBD = this.producerPersistence.read("New Line Cinema Inc.");
        assertEquals("New Line Cinema Inc.",producerBD.getBusinessName());
        assertEquals("newline@cinema.com",producerBD.getEmail());
        assertEquals(987654321L,producerBD.getPhone());
    }

    @Test
    void testUpdate() {
        Producer producer = new Producer("New Line Cinema Ltd.","new@line.cinema",333222111L);
        Producer producerBD = this.producerPersistence.update("New Line Cinema Inc.",producer);
        assertEquals("New Line Cinema Ltd.",producerBD.getBusinessName());
        assertEquals("new@line.cinema",producerBD.getEmail());
        assertEquals(333222111L,producerBD.getPhone());
    }

    @Test
    void testFindProducerPhonesByTvSeriesYearAndPlayerNationality() {
        assertEquals(List.of(111222333L),
                this.producerPersistence.findProducerPhonesByTvSeriesYearAndPlayerNationality(2013,"Japan")
                        .collect(Collectors.toList()));

        assertEquals(List.of(327468273L),
                this.producerPersistence.findProducerPhonesByTvSeriesYearAndPlayerNationality(2009,"United States")
                        .collect(Collectors.toList()));
    }
}