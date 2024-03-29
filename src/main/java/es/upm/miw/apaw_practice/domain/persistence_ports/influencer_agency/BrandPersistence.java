package es.upm.miw.apaw_practice.domain.persistence_ports.influencer_agency;

import es.upm.miw.apaw_practice.domain.models.influencer_agency.Campaign;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Repository
public interface BrandPersistence {

    Stream<Campaign> getCampaignsByBrand(String trademark);

    void updateBrand(String trademark, BigDecimal newBudget);

    BigDecimal sumBudgetsByPlatform(String platform);
}
