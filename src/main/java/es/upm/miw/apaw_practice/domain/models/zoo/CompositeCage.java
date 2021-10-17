package es.upm.miw.apaw_practice.domain.models.zoo;

import java.util.List;
import java.util.stream.Collectors;

public class CompositeCage implements CageGroup {

    private List<CageGroup> cages;

    public CompositeCage(List<CageGroup> cages) {
        this.cages = cages;
    }

    @Override
    public String getLocationCode() {
        return cages.stream()
                .map(CageGroup::getLocationCode)
                .collect(Collectors.joining());
    }
}
