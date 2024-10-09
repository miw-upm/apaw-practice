package es.upm.miw.apaw_practice.adapters.mongodb.bank.entities;

import es.upm.miw.apaw_practice.domain.models.bank.InvestmentFund;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document
public class InvestmentFundEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private BigDecimal totalCapital;
    private Integer assets;

    public InvestmentFundEntity() {
        //Empty for framework
    }

    public InvestmentFundEntity(InvestmentFund investmentFund) {
        BeanUtils.copyProperties(investmentFund, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(BigDecimal totalCapital) {
        this.totalCapital = totalCapital;
    }

    public Integer getAssets() {
        return assets;
    }

    public void setAssets(Integer assets) {
        this.assets = assets;
    }

    public InvestmentFund toInvestmentFund() {
        InvestmentFund investmentFund = new InvestmentFund();
        BeanUtils.copyProperties(this, investmentFund);
        return investmentFund;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((InvestmentFundEntity) obj).id));
    }

    @Override
    public String toString() {
        return "InvestmentFund{" +
                "name='" + name + '\'' +
                ", totalCapital='" + totalCapital + '\'' +
                ", assets=" + assets +
                '}';
    }

}
