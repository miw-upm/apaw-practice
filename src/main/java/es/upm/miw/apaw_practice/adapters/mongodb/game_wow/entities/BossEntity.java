package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities;

import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ArticleItemEntity;
import es.upm.miw.apaw_practice.domain.models.game_wow.Boss;
import es.upm.miw.apaw_practice.domain.models.game_wow.Drop;
import es.upm.miw.apaw_practice.domain.models.shop.ArticleItem;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class BossEntity {

    @Id
    private String id;
    private String description;
    private String effort;
    @DBRef
    private List<DropEntity> dropList;

    public BossEntity() {
        //empty for framework
    }

    public BossEntity(String description, String effort, List<DropEntity> dropList) {
        this.description = description;
        this.effort = effort;
        this.dropList = dropList;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffort() {
        return effort;
    }

    public void setEffort(String effort) {
        this.effort = effort;
    }

    public List<DropEntity> getDropList() {
        return dropList;
    }

    public void setDropList(List<DropEntity> dropList) {
        this.dropList = dropList;
    }

    public Boss toBoss() {
        Boss boss = new Boss();
        BeanUtils.copyProperties(this, boss, "dropList");
        List<Drop> dropItems = this.dropList.stream()
                .map(DropEntity::todrop)
                .collect(Collectors.toList());
        boss.setDropList(dropItems);
        return boss;

    }
}
