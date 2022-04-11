package com.form;

import com.dao.DaoFactory;
import com.dao.VilleDao;
import com.entities.VilleFrance;

public class VillePost {

    VilleFrance ville;
    private DaoFactory dao;

    public VillePost(VilleFrance ville){
        this.ville = ville;
        this.dao = DaoFactory.getInstance();
    }

    public void postVille(){
        VilleDao ville = this.dao.getVilleDao();
        ville.postVille(this.ville);
    }
}
