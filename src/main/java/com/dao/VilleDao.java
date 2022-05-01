package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.entities.VilleFrance;

public class VilleDao {

    private final DaoFactory daoFactory;
    private static final String AND = " AND ";
    private static final String CODE = "Code_commune_INSEE";
    private static final String NOM = "Nom_commune";
    private static final String CODEP = "Code_postal";
    private static final String LIBEL = "Libelle_acheminement";
    private static final String LIGNE5 = "Ligne_5";
    private static final String LAT = "Latitude";
    private static final String LON = "Longitude";
    private static final String[] ATTRIBUTES_NAMES = new String[]{CODE,NOM,CODEP,LIBEL,LIGNE5,LAT,LON};


    public VilleDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<VilleFrance> getVilles(String codeCommune, String nomCommune, String codePostal, String libelle, String ligne5,
                                       String latitude, String longitude) throws SQLException {
        String[] attributes = new String[]{codeCommune,nomCommune,codePostal,libelle,ligne5,latitude,longitude};
        String query = getQuery(attributes);
        List<VilleFrance> villes = new ArrayList<>();
        try(Connection connexion = daoFactory.getConnection(); Statement statement = connexion.createStatement()) {
            try (ResultSet resultat = statement.executeQuery(query)) {
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
        }  catch (SQLException e){
                e.printStackTrace();
            }
        return villes;
    }

    public String getQuery(String[] attributes){
        StringBuilder query= new StringBuilder("SELECT * from ville_france");
        int cpt = 0;
        for (String attribute : attributes) {
            if (attribute != null) {
                cpt++;
            }
        }
        if(cpt>0) {
            query.append(" WHERE ");
            int test = 0;
            while (attributes[test] == null) {
                test += 1;
            }
            query.append(ATTRIBUTES_NAMES[test]).append("=").append(attributes[test]);
            for (int j = test+1; j < ATTRIBUTES_NAMES.length; j++) {
                if (attributes[j] != null) {
                    query.append(AND).append(ATTRIBUTES_NAMES[j]).append("=").append(attributes[j]);
                }
            }
        }
        query.append(" ORDER BY Nom_Commune ASC;");
        return query.toString();
    }

    public void postVille(VilleFrance ville) throws SQLException {
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
        }finally {
            assert preparedStatement != null;
            preparedStatement.close();
        }
    }


    public void deleteVille(String idDelete) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM ville_france WHERE Code_commune_INSEE=" +
                    idDelete + ";");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            assert preparedStatement != null;
            preparedStatement.close();
        }
    }

    public boolean villeExiste(String codeCommune) throws SQLException {
        boolean existe = false;
        try(Connection connexion = daoFactory.getConnection(); Statement statement = connexion.createStatement() ){
            try(ResultSet resultat = statement.executeQuery("SELECT * FROM ville_france WHERE Code_commune_INSEE=" +
                    codeCommune+";");) {
                while (resultat.next()) {
                    if (resultat.getString(CODE).equals(codeCommune)) {
                        existe = true;
                    }
                }
            } catch (SQLException e){
                    e.printStackTrace();
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

    public void editVille(VilleFrance ville) throws SQLException {
        Connection connexion;
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
        }finally {
            assert preparedStatement != null;
            preparedStatement.close();
        }

    }
}
