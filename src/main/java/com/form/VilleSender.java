package com.form;

import com.dao.DaoFactory;
import com.dao.VilleDao;

public class VilleSender {

    String[] valuesAndInt;
    String[] values;
    private DaoFactory dao;

    public VilleSender(String request){
        this.values = new String[7];
        request = request.replace("+"," ");
        this.valuesAndInt = request.split("&");
        for(int i=0; i<this.valuesAndInt.length; i++){
            String[] valueAndInt = this.valuesAndInt[i].split("=");
            if(valueAndInt.length == 2){
                this.values[i]= valueAndInt[1];
            }
            else{
                this.values[i] = "";
            }
        }
        this.dao = DaoFactory.getInstance();
    }

    public void sendVille(){
        VilleDao ville = this.dao.getVilleDao();
        ville.postVille(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
    }
}
