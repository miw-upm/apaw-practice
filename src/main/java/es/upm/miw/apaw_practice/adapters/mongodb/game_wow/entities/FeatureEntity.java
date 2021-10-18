package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities;

import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class FeatureEntity {

    @Id
    private String id;
    private String part;
    private Integer spellPower;
    private Integer meleeAtack;
    private Integer temple;
    private String extraSpell;

    public FeatureEntity() {
        //empty for framework
    }

    public FeatureEntity(Feature feature) {
        BeanUtils.copyProperties(feature,this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public Integer getSpellPower() {
        return spellPower;
    }

    public void setSpellPower(Integer spellPower) {
        this.spellPower = spellPower;
    }

    public Integer getMeleeAtack() {
        return meleeAtack;
    }

    public void setMeleeAtack(Integer meleeAtack) {
        this.meleeAtack = meleeAtack;
    }

    public Integer getTemple() {
        return temple;
    }

    public void setTemple(Integer temple) {
        this.temple = temple;
    }

    public String getExtraSpell() {
        return extraSpell;
    }

    public void setExtraSpell(String extraSpell) {
        this.extraSpell = extraSpell;
    }

    public Feature toFeature(){
        return new Feature(this.part,this.spellPower,this.meleeAtack,this.temple,this.extraSpell);
    }

    public void fromFeature(Feature feature) {
        BeanUtils.copyProperties(feature, this);
    }
}
