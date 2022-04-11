package com.dao;

import com.entities.VilleFrance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VilleDao {

    private DaoFactory daoFactory;

    public VilleDao(DaoFactory daoFactory) {
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
            System.out.println(preparedStatement);
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
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean villeExiste(String codeCommune){
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
                if(resultat.getString("Code_commune_INSEE").equals(codeCommune)){
                    existe = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }

    public void putVille(VilleFrance ville) {
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
            preparedStatement = connexion.prepareStatement("UPDATE ville_france SET Nom_commune = ?, Code_postal = ?, " +
                    "Libelle_acheminement = ?, Ligne_5 = ?, Latitude = ?, Longitude = ? WHERE Code_commune_INSEE = ? ;");
            System.out.println(preparedStatement);
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
