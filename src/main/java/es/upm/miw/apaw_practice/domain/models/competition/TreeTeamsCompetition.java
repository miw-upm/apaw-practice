package es.upm.miw.apaw_practice.domain.models.competition;

import java.util.List;

public interface TreeTeamsCompetition {

    boolean isComposite();

    void add(TreeTeamsCompetition treeTeamsCompetition);

    void remove(TreeTeamsCompetition treeTeamsCompetition);

    List<TreeTeamsCompetition> getChildren();

    List<PlayerTeam> getAllPlayers();
}
