package br.com.b2w.swapi.planet.service;

import java.util.List;

import br.com.b2w.swapi.planet.model.Planet;

public interface IPlanetService {
     List<Planet> getPlanets();
     Planet getPlanet(String id);
     Planet save(Planet planet);
     void delete(Planet planet);
}
