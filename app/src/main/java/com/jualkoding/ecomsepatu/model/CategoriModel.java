package com.jualkoding.ecomsepatu.model;

public class CategoriModel {

    private int id;
    private String labels;

    public CategoriModel(int id, String labels) {
        this.id = id;
        this.labels = labels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
