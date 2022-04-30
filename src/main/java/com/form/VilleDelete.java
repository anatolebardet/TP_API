package com.form;

import com.dao.DaoFactory;
import com.dao.VilleDao;

import java.sql.SQLException;

public class VilleDelete {
    private String idDelete;
    private DaoFactory dao;

    public VilleDelete(String code){
        this.idDelete = code;
        this.dao = DaoFactory.getInstance();
    }

    public void deleteVille() throws SQLException {
        VilleDao ville = this.dao.getVilleDao();
        ville.deleteVille(this.idDelete);
    }
}
