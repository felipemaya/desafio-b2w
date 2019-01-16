package br.com.b2w.swapi.planet.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.b2w.swapi.planet.model.Planet;
import br.com.b2w.swapi.planet.repository.IPlanetRepository;

@Component("planetValidator")
public class PlanetValidator implements Validator {

	@Autowired
	private IPlanetRepository planetRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return Planet.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Planet planet = (Planet)obj;

		if(StringUtils.isEmpty(planet.getNome())) {
			errors.rejectValue("nome", "form.planet.nome");
		}
		
		if(StringUtils.isEmpty(planet.getTerreno())) {
			errors.rejectValue("terreno", "form.planet.terreno");
		}
		
		if(StringUtils.isEmpty(planet.getClima())) {
			errors.rejectValue("clima", "form.planet.clima");
		}
		
		if(!errors.hasErrors()) {
			List<Planet> list = planetRepository.findPlanetByName(planet.getNome());
			if(!list.isEmpty()) {
				Planet planetValidate = list.get(0);
				if(planet.getId()==null || !planetValidate.getId().equals(planet.getId())) {					
					errors.rejectValue("nome", "form.planet.nome.nok");
				}
			}
		}
		
	}
}
