package es.upm.miw.apaw_practice.domain.models.bank;

import java.math.BigDecimal;

public class InvestmentFund {

    private String name;
    private BigDecimal totalCapital;
    private Integer assets;

    public InvestmentFund() {
        //Empty for framework
    }

    public InvestmentFund(String name, BigDecimal totalCapital, Integer assets) {
        this.name = name;
        this.totalCapital = totalCapital;
        this.assets = assets;
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

    @Override
    public String toString() {
        return "InvestmentFund{" +
                "name='" + name + '\'' +
                ", totalCapital='" + totalCapital + '\'' +
                ", assets=" + assets +
                '}';
    }
}
