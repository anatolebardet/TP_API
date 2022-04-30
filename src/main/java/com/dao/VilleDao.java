package com.dao;

import com.entities.VilleFrance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VilleDao {

    private final DaoFactory daoFactory;
    public static final String AND = " AND ";
    public static final String CODE = "Code_commune_INSEE";
    public static final String NOM = "Nom_commune";
    public static final String CODEP = "Code_postal";
    public static final String LIBEL = "Libelle_acheminement";
    public static final String LIGNE5 = "Ligne_5";
    public static final String LAT = "Latitude";
    public static final String LON = "Longitude";


    public VilleDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
/*
    public List<VilleFrance> getVillesCP(String codePostal){
        Connection connexion;
        Statement statement;
        ResultSet resultat;
        List<VilleFrance> villes = new ArrayList<>();
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM ville_france WHERE "+ CODEP+ "=" + codePostal + ";");
            while (resultat.next()) {
                villes.add(new VilleFrance(resultat.getString(Code),
                        resultat.getString(Nom),
                        resultat.getString(CodeP),
                        resultat.getString(Libel),
                        resultat.getString(Ligne5),
                        resultat.getString(Lat),
                        resultat.getString(Lon)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return villes;
    }
*/
    public List<VilleFrance> getAllVilles(){
        Connection connexion;
        Statement statement;
        ResultSet resultat;
        List<VilleFrance> villes = new ArrayList<>();
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM ville_france ORDER BY Nom_Commune ASC;");
            while (resultat.next()) {
                villes.add(new VilleFrance(resultat.getString(CODE),
                        resultat.getString(NOM),
                        resultat.getString(CODEP),
                        resultat.getString(LIBEL),
                        resultat.getString(LIGNE5),
                        resultat.getString(LAT),
                        resultat.getString(LON)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return villes;
    }

    public List<VilleFrance> getVilles(String codeCommune, String nomCommune, String codePostal, String libelle, String ligne5,
                                       String latitude, String longitude){
        String query = getQuery(codeCommune, nomCommune, codePostal, libelle, ligne5, latitude, longitude);
        Connection connexion;
        Statement statement;
        ResultSet resultat;
        List<VilleFrance> villes = new ArrayList<>();
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(query);
            while (resultat.next()) {
                villes.add(new VilleFrance(resultat.getString(CODE),
                        resultat.getString(NOM),
                        resultat.getString(CODEP),
                        resultat.getString(LIBEL),
                        resultat.getString(LIGNE5),
                        resultat.getString(LAT),
                        resultat.getString(LON)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return villes;
    }

    public String getQuery(String codeCommune, String nomCommune, String codePostal, String libelle, String ligne5,
    String latitude, String longitude){
        String query="SELECT * from ville_france";
        boolean andQuery = false;
        if(codeCommune != null){
            query += "Code_commune_INSEE="+codeCommune;
            andQuery = true;
        }
        if(nomCommune != null){
            if(andQuery){
                query += AND + "Nom_commune=" + nomCommune;
            }
            else{
                query += " WHERE Nom_commune=" + nomCommune;
                andQuery = true;
            }
        }
        if(codePostal != null){
            if(andQuery){
                query += AND + "Code_postal=" + codePostal;
            }
            else{
                query += " WHERE Code_postal=" + codePostal;
                andQuery = true;
            }
        }
        if(libelle != null){
            if(andQuery){
                query += AND + "Libelle_acheminement=" + libelle;
            }
            else{
                query += " WHERE Libelle_acheminement=" + libelle;
                andQuery = true;
            }
        }
        if(ligne5 != null){
            if(andQuery){
                query += AND + "Ligne_5=" + ligne5;
            }
            else{
                query += " WHERE Ligne_5=" + ligne5;
                andQuery = true;
            }
        }
        if(latitude != null){
            if(andQuery){
                query += AND + LAT + latitude;
            }
            else{
                query += " WHERE Latitude" + latitude;
                andQuery = true;
            }
        }
        if(longitude != null){
            if(andQuery){
                query += AND + LON + longitude;
            }
            else{
                query += " WHERE Longitude" + longitude;
            }
        }
        query += " ORDER BY Nom_Commune ASC;";
        return query;
    }

    public void postVille(VilleFrance ville) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO ville_france(Code_commune_INSEE, Nom_commune, " +
                    "Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) VALUES(?,?,?,?,?,?,?);");
            preparedStatement.setString(1, ville.getId());
            preparedStatement.setString(2, ville.getNomCommune());
            preparedStatement.setString(3, ville.getCodePostal());
            preparedStatement.setString(4, ville.getLibelleAcheminement());
            preparedStatement.setString(5, ville.getLigne5());
            preparedStatement.setString(6, ville.getLatitude());
            preparedStatement.setString(7, ville.getLongitude());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteVille(String idDelete) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM ville_france WHERE Code_commune_INSEE=" +
                    idDelete + ";");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean villeExiste(String codeCommune) throws SQLException {
        boolean existe = false;
        Connection connexion;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM ville_france WHERE Code_commune_INSEE=" +
                    codeCommune+";");
            while (resultat.next()) {
                if(resultat.getString(CODE).equals(codeCommune)){
                    existe = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }

    public void putVille(VilleFrance ville) throws SQLException {
        if(villeExiste(ville.getId())){
            editVille(ville);
        }
        else{
            postVille(ville);
        }
    }

    public void editVille(VilleFrance ville){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE ville_france SET Nom_commune = ?, " +
                    "Code_postal = ?, Libelle_acheminement = ?, Ligne_5 = ?, Latitude = ?, Longitude = ? WHERE " +
                    "Code_commune_INSEE = ? ;");
            preparedStatement.setString(1, ville.getNomCommune());
            preparedStatement.setString(2, ville.getCodePostal());
            preparedStatement.setString(3, ville.getLibelleAcheminement());
            preparedStatement.setString(4, ville.getLigne5());
            preparedStatement.setString(5, ville.getLatitude());
            preparedStatement.setString(6, ville.getLongitude());
            preparedStatement.setString(7, ville.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
