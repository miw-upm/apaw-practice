package es.upm.miw.apaw_practice.domain.models.tennis_courts.compositePlayer;

import java.util.ArrayList;
import java.util.List;

public class TreePlayerComposite implements TreePlayer{

    private final String dni;
    private final String reference;
    private final List<TreePlayer> treePlayerList;

    public TreePlayerComposite(String dni, String reference){
        this.dni = dni;
        this.reference = reference;
        this.treePlayerList = new ArrayList<>();
    }

    @Override
    public String dni() {
        return this.dni;
    }

    @Override
    public String reference() {
        return this.reference;
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(TreePlayer treePlayer) {
        this.treePlayerList.add(treePlayer);
    }

    @Override
    public void remove(TreePlayer treePlayer) {
        this.treePlayerList.remove(treePlayer);
    }
}
