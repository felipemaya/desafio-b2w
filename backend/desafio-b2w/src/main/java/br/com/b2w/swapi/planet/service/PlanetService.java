package br.com.b2w.swapi.planet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapi.models.SWModelList;
import com.swapi.sw.StarWars;

import br.com.b2w.swapi.planet.model.Planet;
import br.com.b2w.swapi.planet.repository.IPlanetRepository;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class PlanetService implements IPlanetService {

	@Autowired
	private IPlanetRepository planetRepository;
	
	@Autowired
	private StarWars starWarsApi;
	
	@Override
	public List<Planet> getPlanets() {
		return planetRepository.findAll();
	}

	@Override
	public Planet getPlanet(String id) {
		List<Planet> list = planetRepository.findPlanetById(id);
		return list.isEmpty()?null:list.get(0);
	}

	@Override
	public Planet save(Planet planet) {
		Call<SWModelList<com.swapi.models.Planet>> planets = starWarsApi.getAllPlanets(null);

        Response<SWModelList<com.swapi.models.Planet>> response;
		try {
			response = planets.execute();
			if (response.isSuccessful()) {
	            SWModelList<com.swapi.models.Planet> data = response.body();
	            Integer numFilms = 0;
	            for (com.swapi.models.Planet planetSW : data.results) {
	            	if(planetSW.name.equalsIgnoreCase(planet.getNome())) {
	            		numFilms = planetSW.filmsUrls.size(); 
	            		break;
	            	}
	            }
	            planet.setFilmes(numFilms);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return planetRepository.save(planet);
	}

	@Override
	public void delete(Planet planet) {
		planetRepository.delete(planet);
	}
}
