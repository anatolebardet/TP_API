package com.entities;

public class VilleFrance {

    private String id;
    private String nomCommune;
    private String codePostal;
    private String libelleAcheminement;
    private String ligne5;
    private String latitude;
    private String longitude;

    public VilleFrance(){
    }

    public VilleFrance(String id){
        this.id=id;
    }

    public VilleFrance(String id, String nomCommune, String codePostal, String libelleAcheminement, String ligne5,
                       String latitude, String longitude){
        this.id=id;
        this.nomCommune = nomCommune;
        this.codePostal = codePostal;
        this.libelleAcheminement = libelleAcheminement;
        this.ligne5 = ligne5;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLigne5() {
        return ligne5;
    }

    public void setLigne5(String ligne5) {
        this.ligne5 = ligne5;
    }

    public String getLibelleAcheminement() {
        return libelleAcheminement;
    }

    public void setLibelleAcheminement(String libelleAcheminement) {
        this.libelleAcheminement = libelleAcheminement;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}