package com.form;

import com.dao.DaoFactory;
import com.dao.VilleDao;
import com.entities.VilleFrance;

import java.sql.SQLException;

public class VillePost {

    VilleFrance ville;
    private DaoFactory dao;

    public VillePost(VilleFrance ville){
        this.ville = ville;
        this.dao = DaoFactory.getInstance();
    }

    public void postVille() throws SQLException {
        VilleDao villePost  = this.dao.getVilleDao();
        villePost.postVille(this.ville);
    }
}
