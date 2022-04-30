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

	@GetMapping(value="/villes", produces ="application/json;charset=UTF-8")
	public List<VilleFrance> get(@RequestParam(required  = false,value="Code_commune_INSEE") String codeCommune,
								@RequestParam(required  = false,value="Nom_commune") String nomCommune,
								@RequestParam(required  = false,value="Code_postal") String codePostal,
								@RequestParam(required  = false,value="Libelle_acheminement") String libelle,
								@RequestParam(required  = false,value="Ligne_5") String ligne5,
								@RequestParam(required  = false,value="Latitude") String latitude,
								@RequestParam(required  = false,value="Longitude") String longitude) throws SQLException {

		DaoFactory daoFactory = DaoFactory.getInstance();
		VilleDao villeDao = daoFactory.getVilleDao();
		return villeDao.getVilles(codeCommune,nomCommune,codePostal,libelle,ligne5,latitude,longitude);
	}


	@PostMapping(value = "/ville")
	@ResponseBody
	public void post(@RequestParam("Code_commune_INSEE") String codeCommune,
					@RequestParam("Nom_commune") String nomCommune,
					@RequestParam("Code_postal") String codePostal,
					@RequestParam("Libelle_acheminement") String libelle,
					@RequestParam("Ligne_5") String ligne5,
					@RequestParam("Latitude") String latitude,
					@RequestParam("Longitude") String longitude) throws SQLException {
		VilleFrance ville = new VilleFrance(codeCommune, nomCommune, codePostal, libelle, ligne5, latitude, longitude);
		VillePost villePost = new VillePost(ville);
		villePost.postVille();
	}

	@DeleteMapping(value = "/ville")
	@ResponseBody
	public void delete(@RequestParam("Code_commune_INSEE") String codeCommuneINSEE) throws SQLException {
		VilleDelete ville = new VilleDelete(codeCommuneINSEE);
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