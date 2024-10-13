package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.time.LocalDate;

public interface CustomerTree {
    String getEmail();
    String getName();
    LocalDate getBirthDate();
    boolean isMember();
    Membership getMembership();
    boolean isComposite();
    void add(CustomerTree customerTree);
    void remove(CustomerTree customerTree);
}
