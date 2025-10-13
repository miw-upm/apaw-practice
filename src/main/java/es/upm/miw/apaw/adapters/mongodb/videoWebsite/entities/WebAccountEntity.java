package es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.videoWebsite.WatchList;
import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.AccountType;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


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

    public WebAccountEntity(WebAccount webAccount){
        BeanUtils.copyProperties(webAccount, this, "userId", "watchListEntities");
        this.userId = webAccount.getUser().getId();
        this.watchListEntities = webAccount.getWatchList().stream()
                .map(WatchListEntity::new)
                .toList();

    }

    public WebAccount toWebAccount() {
        WebAccount webAccount = new WebAccount();

        BeanUtils.copyProperties(this, webAccount, "user", "userId", "watchListEntities", "watchList");
        webAccount.setUser(UserDto.builder().id(this.userId).build());

        List<WatchList> watchLists = this.watchListEntities.stream()
                .map(WatchListEntity::toWatchList)
                .toList();

        webAccount.setWatchList(watchLists);
        return webAccount;

    }
}
