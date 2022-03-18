package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VilleDao {

    private DaoFactory daoFactory;

    VilleDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<String> getVillesCP(String codePostal){
        Connection connexion;
        Statement statement;
        ResultSet resultat;
        List<String> villes = new ArrayList<String>();
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM ville_france WHERE Code_postal=" + codePostal + ";");
            while (resultat.next()) {
                villes.add(resultat.getString("Nom_commune"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return villes;
    }

    public List<String> getVilles(){
        Connection connexion;
        Statement statement;
        ResultSet resultat;
        List<String> villes = new ArrayList<String>();
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM ville_france ORDER BY Nom_Commune ASC;");
            while (resultat.next()) {
                villes.add(resultat.getString("Nom_commune"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return villes;
    }

    public void postVille(String codeCommune, String nomCommune,String codePostal,String libelle,String ligne5,
                          String latitude,String longitude) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            preparedStatement = connexion.prepareStatement("INSERT INTO ville_france(Code_commune_INSEE, Nom_commune, " +
                    "Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) VALUES(?,?,?,?,?,?,?);");
            preparedStatement.setString(1, codeCommune);
            preparedStatement.setString(2, nomCommune);
            preparedStatement.setString(3, codePostal);
            preparedStatement.setString(4, libelle);
            preparedStatement.setString(5, ligne5);
            preparedStatement.setString(6, latitude);
            preparedStatement.setString(7, longitude);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




}
