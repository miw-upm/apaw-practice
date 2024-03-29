package es.upm.miw.apaw_practice.adapters.mongodb.influencer_agency.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.influencer_agency.daos.BrandRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.influencer_agency.Campaign;
import es.upm.miw.apaw_practice.domain.persistence_ports.influencer_agency.BrandPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.adapters.mongodb.influencer_agency.entities.CampaignEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.influencer_agency.entities.BrandEntity;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

@Repository("brandPersistence")
public class BrandPersistenceMongodb implements BrandPersistence {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandPersistenceMongodb(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Stream<Campaign> getCampaignsByBrand(String trademark) {
        return this.brandRepository.findByTrademark(trademark)
                .orElseThrow(() -> new NotFoundException("Brand trademark: " + trademark))
                .getCampaigns().stream().map(CampaignEntity::toCampaign);
    }

    @Override
    public void updateBrand(String trademark, BigDecimal newBudget) {
        this.brandRepository.findByTrademark(trademark)
                .ifPresentOrElse(
                        brand -> {
                            brand.setAdvertisingBudget(newBudget);
                            this.brandRepository.save(brand);
                        },
                        () -> {
                            throw new NotFoundException("Brand trademark: " + trademark);
                        }
                );
    }

    @Override
    public BigDecimal sumBudgetsByPlatform(String platform) {
        return Optional.of(this.brandRepository.findAll().stream()
                        .filter(brand -> brand.getCampaigns().stream()
                                .anyMatch(campaign -> campaign.getContents().stream()
                                        .anyMatch(content -> content.getPlatform().equals(platform))
                                )
                        )
                        .map(BrandEntity::getAdvertisingBudget)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .filter(budget -> !budget.equals(BigDecimal.ZERO))
                .orElseThrow(() -> new NotFoundException("No brands found with campaigns on the given platform: " + platform));
    }


}
