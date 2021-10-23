package es.upm.miw.apaw_practice.domain.models.music_manager;

import java.util.List;

public interface TreeAlbums {
    String albumTitle();

    boolean isComposite();

    void add(TreeAlbums treeAlbums);

    void remove(TreeAlbums treeAlbums);

    List<Album> getAlbums();
}
