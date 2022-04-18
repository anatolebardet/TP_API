package com.controller;

import com.dao.DaoFactory;
import com.dao.VilleDao;
import com.entities.VilleFrance;
import com.form.VilleDelete;
import com.form.VillePost;
import com.form.VillePut;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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

	@RequestMapping(value="/villes", method=RequestMethod.GET)
	public void get(@RequestParam("Code_commune_INSEE") String codeCommune,
					@RequestParam("Nom_commune") String nomCommune,
					@RequestParam(value="Code_postal") String codePostal,
					@RequestParam(value="Libelle_acheminement") String libelle,
					@RequestParam(value="Ligne_5") String ligne5,
					@RequestParam(value="Latitude") String latitude,
					@RequestParam(value="Longitude") String longitude){
		VilleFrance ville = new VilleFrance(codeCommune, nomCommune, codePostal, libelle, ligne5, latitude, longitude);
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.villeDao = daoFactory.getVilleDao();
		villeDao.postVille(ville);
	}

	@PostMapping(value = "/ville")
	@ResponseBody
	public void post(@RequestParam("Code_commune_INSEE") String codeCommune,
					@RequestParam("Nom_commune") String nomCommune,
					@RequestParam("Code_postal") String codePostal,
					@RequestParam("Libelle_acheminement") String libelle,
					@RequestParam("Ligne_5") String ligne5,
					@RequestParam("Latitude") String latitude,
					@RequestParam("Longitude") String longitude) {
		VilleFrance ville = new VilleFrance(codeCommune, nomCommune, codePostal, libelle, ligne5, latitude, longitude);
		VillePost villePost = new VillePost(ville);
		villePost.postVille();
	}

	@DeleteMapping(value = "/ville")
	@ResponseBody
	public void delete(@RequestParam("Code_commune_INSEE") String Code_commune_INSEE) {
		System.out.println("Code : " + Code_commune_INSEE);
		VilleDelete ville = new VilleDelete(Code_commune_INSEE);
		ville.deleteVille();
	}

	@PutMapping(value = "/ville")
	@ResponseBody
	public void put(@RequestParam("Code_commune_INSEE") String codeCommune,
					@RequestParam("Nom_commune") String nomCommune,
					@RequestParam("Code_postal") String codePostal,
					@RequestParam("Libelle_acheminement") String libelle,
					@RequestParam("Ligne_5") String ligne5,
					@RequestParam("Latitude") String latitude,
					@RequestParam("Longitude") String longitude) throws SQLException {
		VilleFrance ville = new VilleFrance(codeCommune, nomCommune, codePostal, libelle, ligne5, latitude, longitude);
		VillePut villePut = new VillePut(ville);
		villePut.putVille();
	}

}