package com.jualkoding.ecomsepatu.model;

public class HomeModel {

    private int idproduct;
    private String namaproduct;
    private String statusproduct;
    private String diskonproduct;
    private String imageproduct;

    public HomeModel(int idproduct, String namaproduct, String statusproduct, String diskonproduct, String imageproduct) {
        this.idproduct = idproduct;
        this.namaproduct = namaproduct;
        this.statusproduct = statusproduct;
        this.diskonproduct = diskonproduct;
        this.imageproduct = imageproduct;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public String getNamaproduct() {
        return namaproduct;
    }

    public void setNamaproduct(String namaproduct) {
        this.namaproduct = namaproduct;
    }

    public String getStatusproduct() {
        return statusproduct;
    }

    public void setStatusproduct(String statusproduct) {
        this.statusproduct = statusproduct;
    }

    public String getDiskonproduct() {
        return diskonproduct;
    }

    public void setDiskonproduct(String diskonproduct) {
        this.diskonproduct = diskonproduct;
    }

    public String getImageproduct() {
        return imageproduct;
    }

    public void setImageproduct(String imageproduct) {
        this.imageproduct = imageproduct;
    }
}
