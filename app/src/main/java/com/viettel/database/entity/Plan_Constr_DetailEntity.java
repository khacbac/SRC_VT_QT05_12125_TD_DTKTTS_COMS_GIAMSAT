package com.viettel.database.entity;

/**
 * Created by hieppq3 on 5/15/17.
 */

public class Plan_Constr_DetailEntity extends BaseEntity {


    private long id;
    private long constructId;
    private long planId;
    private String planName;
    private String finishDate;
    private String realFinishDate;
    private double realValue;
    private String milestoneName;
    private long catMilestoneConstrId;
    private String expectedValue;


    public String getRealFinishDate() {
        return realFinishDate;
    }

    public void setRealFinishDate(String realFinishDate) {
        this.realFinishDate = realFinishDate;
    }

    public long getCatMilestoneConstrId() {
        return catMilestoneConstrId;
    }

    public void setCatMilestoneConstrId(long catMilestoneConstrId) {
        this.catMilestoneConstrId = catMilestoneConstrId;
    }

    public double getRealValue() {
        return realValue;
    }

    public void setRealValue(double realValue) {
        this.realValue = realValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getConstructId() {
        return constructId;
    }

    public void setConstructId(long constructId) {
        this.constructId = constructId;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }
}
