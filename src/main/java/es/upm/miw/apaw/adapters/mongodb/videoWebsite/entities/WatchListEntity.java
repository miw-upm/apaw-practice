package es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities;

import es.upm.miw.apaw.domain.models.videoWebsite.WatchList;
import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchListEntity {
    @DBRef
    private List<VideoEntity> savedVideoEntities;
    private String listName;
    private String description;

    //Pasar Entity al modelo dominio
    public WatchList toWatchList() {
        WatchList watchList = new WatchList();
        BeanUtils.copyProperties(this, watchList, "savedVideos");

        List<Video> videos = this.savedVideoEntities
                .stream()
                .map(VideoEntity::toVideo)
                .toList();

        watchList.setSavedVideos(videos);
        return watchList;
    }

    public WatchListEntity (WatchList watchList) {
        BeanUtils.copyProperties(watchList, this, "savedVideoEntities");
        this.savedVideoEntities = watchList.getSavedVideos()
                .stream()
                .map(VideoEntity::new)
                .toList();

    }

}
