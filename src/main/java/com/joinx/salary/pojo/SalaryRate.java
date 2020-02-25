package com.joinx.salary.pojo;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * salary_rate
 * @author 
 */
public class SalaryRate implements Serializable {
    private Integer id;

    private Double endowmentInsuranceRate;

    private Double mdeicareRate;

    private Double unemploymentRate;

    private Double injuryInsuranceRate;

    private Double rearInsuranceRate;

    private Double reservedFundRate;

    private String description;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getEndowmentInsuranceRate() {
        return endowmentInsuranceRate;
    }

    public void setEndowmentInsuranceRate(Double endowmentInsuranceRate) {
        this.endowmentInsuranceRate = endowmentInsuranceRate;
    }

    public Double getMdeicareRate() {
        return mdeicareRate;
    }

    public void setMdeicareRate(Double mdeicareRate) {
        this.mdeicareRate = mdeicareRate;
    }

    public Double getUnemploymentRate() {
        return unemploymentRate;
    }

    public void setUnemploymentRate(Double unemploymentRate) {
        this.unemploymentRate = unemploymentRate;
    }

    public Double getInjuryInsuranceRate() {
        return injuryInsuranceRate;
    }

    public void setInjuryInsuranceRate(Double injuryInsuranceRate) {
        this.injuryInsuranceRate = injuryInsuranceRate;
    }

    public Double getRearInsuranceRate() {
        return rearInsuranceRate;
    }

    public void setRearInsuranceRate(Double rearInsuranceRate) {
        this.rearInsuranceRate = rearInsuranceRate;
    }

    public Double getReservedFundRate() {
        return reservedFundRate;
    }

    public void setReservedFundRate(Double reservedFundRate) {
        this.reservedFundRate = reservedFundRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}