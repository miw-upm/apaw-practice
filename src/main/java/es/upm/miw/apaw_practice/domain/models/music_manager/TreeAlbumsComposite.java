package es.upm.miw.apaw_practice.domain.models.music_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeAlbumsComposite implements TreeAlbums {

    private final String albumTitle;

    private final List<TreeAlbums> treeAlbumsList;

    public TreeAlbumsComposite(String albumTitle) {
        this.albumTitle = albumTitle;
        this.treeAlbumsList = new ArrayList<>();
    }

    @Override
    public String albumTitle() {
        return this.albumTitle;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(TreeAlbums treeAlbums) {
        this.treeAlbumsList.add(treeAlbums);
    }

    @Override
    public void remove(TreeAlbums treeAlbums) {
        this.treeAlbumsList.remove(treeAlbums);
    }

    @Override
    public List<Album> getAlbums() {
        return this.treeAlbumsList.stream()
                .flatMap(treeAlbums -> treeAlbums.getAlbums().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
