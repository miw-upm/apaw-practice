package es.upm.miw.apaw_practice.domain.models.competition;

import java.math.BigDecimal;

public interface PlayerTeamBuilders {

    interface Id {
        Weight id(String id);
    }

    interface Weight {
        Height weight(Double weight);
    }

    interface Height {
        Salary height(Double height);
    }

    interface Salary {
        Optionals salary(BigDecimal salary);
    }

    interface Optionals {
        PlayerTeam build();
    }
}
