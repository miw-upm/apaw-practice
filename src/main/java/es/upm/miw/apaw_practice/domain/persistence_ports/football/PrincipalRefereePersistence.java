package es.upm.miw.apaw_practice.domain.persistence_ports.football;

import es.upm.miw.apaw_practice.domain.models.football.PrincipalReferee;

public interface PrincipalRefereePersistence {
    PrincipalReferee create(PrincipalReferee principalReferee);

    PrincipalReferee readByName(String q);
}
