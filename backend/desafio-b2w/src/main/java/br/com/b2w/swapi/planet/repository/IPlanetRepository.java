package br.com.b2w.swapi.planet.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.b2w.swapi.planet.model.Planet;


@Repository
public interface IPlanetRepository extends MongoRepository<Planet, Long> {
	@Query("{ 'id' : ?0 }")
    List<Planet> findPlanetById(String id);
    
    @Query("{ 'nome' : ?0 }")
    List<Planet> findPlanetByName(String name);
}
