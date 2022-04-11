package com.form;

import com.dao.DaoFactory;
import com.dao.VilleDao;
import com.entities.VilleFrance;

import java.sql.SQLException;

public class VillePut {

    VilleFrance ville;
    private DaoFactory dao;

    public VillePut(VilleFrance ville){
        this.ville = ville;
        this.dao = DaoFactory.getInstance();
    }

    public void putVille() throws SQLException {
        VilleDao ville = this.dao.getVilleDao();
        ville.putVille(this.ville);
    }
}
