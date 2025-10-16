package es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.models.videoWebsite.WatchList;
import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.AccountType;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class WebAccountEntity {
    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    @Indexed(unique = true)
    private String userName;
    private AccountType accountType;

    private UUID userId;
    private List<WatchListEntity> watchListEntities;
    private List<VideoEntity> publishedVideoEntites;

    public WebAccountEntity(WebAccount webAccount){
        BeanUtils.copyProperties(webAccount, this, "userId", "watchListEntities", "publishedVideoEntites");
        this.userId = webAccount.getUser().getId();
        this.watchListEntities = webAccount.getWatchList().stream()
                .map(WatchListEntity::new)
                .toList();
        this.publishedVideoEntites = webAccount.getPublishedVideos().stream()
                .map(VideoEntity::new)
                .toList();
    }

    public WebAccount toWebAccount() {
        WebAccount webAccount = new WebAccount();

        BeanUtils.copyProperties(this, webAccount, "user", "watchList", "publishedVideos");
        webAccount.setUser(UserDto.builder().id(this.userId).build());

        List<WatchList> watchLists = (this.watchListEntities == null)
                ? Collections.emptyList()
                : this.watchListEntities.stream()
                .map(WatchListEntity::toWatchList)
                .toList();

        webAccount.setWatchList(watchLists);
        List<Video> publishedVideos = (this.publishedVideoEntites == null)
                ?Collections.emptyList()
                :this.publishedVideoEntites.stream()
                .map(VideoEntity::toVideo)
                .toList();
        webAccount.setPublishedVideos(publishedVideos);
        return webAccount;

    }
}
