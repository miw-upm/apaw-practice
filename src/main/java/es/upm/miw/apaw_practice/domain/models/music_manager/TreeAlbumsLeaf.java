package es.upm.miw.apaw_practice.domain.models.music_manager;

import java.util.List;

public class TreeAlbumsLeaf implements TreeAlbums {
    private final Album album;

    public TreeAlbumsLeaf(Album album) {
        this.album = album;
    }

    @Override
    public String albumTitle() {
        return this.album.getAlbumTitle();
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(TreeAlbums treeAlbums) {
        // Do nothing because is a leaf
    }

    @Override
    public void remove(TreeAlbums treeAlbums) {
        // Do nothing because is a leaf
    }

    @Override
    public List<Album> getAlbums() {
        return List.of(this.album);
    }
}
