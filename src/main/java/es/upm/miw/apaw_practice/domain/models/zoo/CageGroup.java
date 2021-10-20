package es.upm.miw.apaw_practice.domain.models.zoo;

public interface CageGroup {

    String getLocationCode();

    void add(CageGroup cageGroup);

    void remove(CageGroup cageGroup);
}
