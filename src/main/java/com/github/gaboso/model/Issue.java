package com.github.gaboso.model;

public class Issue {

    private String title;
    private String description;
    private String humanEstimateTime;
    private int estimateTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHumanEstimateTime() {
        return humanEstimateTime;
    }

    public void setHumanEstimateTime(String humanEstimateTime) {
        this.humanEstimateTime = humanEstimateTime;
    }

    public int getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(int estimateTime) {
        this.estimateTime = estimateTime;
    }
}