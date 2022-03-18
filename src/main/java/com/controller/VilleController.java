package com.controller;

import com.dao.DaoFactory;
import com.dao.VilleDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.List;

@RestController
public class VilleController {

	private VilleDao villeDao;

	// fonction pour récupérer le contenu de la BDD
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	public String get(@RequestParam(required  = false, value="codePostal") String codePostal) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.villeDao = daoFactory.getVilleDao();
		List<String> villes = null;
		if(codePostal != null){
			villes = villeDao.getVillesCP(codePostal);
		}
		else{
			villes  = villeDao.getVilles();
		}

		return villes.toString();
	}

	@RequestMapping(value="/ajoutVille", method=RequestMethod.GET)
	public void get(@RequestParam(value="codeCommune") String codeCommune,
					 @RequestParam(value="nomCommune") String nomCommune,
					 @RequestParam(value="codePostal") String codePostal,
					 @RequestParam(value="libelle") String libelle,
					 @RequestParam(value="ligne5") String ligne5,
					 @RequestParam(value="latitude") String latitude,
					 @RequestParam(value="longitude") String longitude){

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.villeDao = daoFactory.getVilleDao();
		villeDao.postVille(codeCommune, nomCommune, codePostal, libelle, ligne5, latitude, longitude);
	}



}