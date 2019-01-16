package br.com.b2w.swapi.planet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.swapi.planet.model.Planet;
import br.com.b2w.swapi.planet.service.IPlanetService;
import br.com.b2w.swapi.spring.config.Messages;
import br.com.b2w.swapi.util.Resultado;

@RestController("/planet")
@RequestMapping("/planet")
@EnableGlobalMethodSecurity(securedEnabled = true)
@CrossOrigin(maxAge = 3600)
public class PlanetController {

	@Autowired
	private IPlanetService planetService;

	@Autowired
	private Messages messages;

	@Autowired
    @Qualifier("planetValidator")
    private Validator validator;
	
	@InitBinder
    private void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
    }
	
	@PostMapping("planet")
	@PreAuthorize("hasRole('NOLOGIN')")
	public ResponseEntity<Resultado> planet(@RequestBody Planet planet) {
		Resultado resultado = new Resultado();
		resultado.setOk(true);
		resultado.setResultado(planetService.getPlanet(planet.getId()));
		return new ResponseEntity<Resultado>(resultado, HttpStatus.OK);
	}

	@PostMapping("save")
	@PreAuthorize("hasRole('NOLOGIN')")
	public ResponseEntity<Resultado> save(@RequestBody @Validated Planet planet, Errors errors) {
		Resultado resultado = new Resultado();
		resultado.setOk(true);
		
		if (errors.hasErrors()) {
			resultado.setMensagens(messages.get(errors));
			resultado.setOk(false);
			resultado.setResultado(planet);
		} else {
			resultado.setResultado(planetService.save(planet));
			resultado.setMensagens(messages.get("form.planet.add.ok"));
		}
		return new ResponseEntity<Resultado>(resultado, HttpStatus.OK);
	}

	@PostMapping("delete")
	@PreAuthorize("hasRole('NOLOGIN')")
	public ResponseEntity<Resultado> delete(@RequestBody Planet planet, Errors errors) {
		Resultado resultado = new Resultado();
		resultado.setOk(true);
		
		if (errors.hasErrors()) {
			resultado.setMensagens(messages.get(errors));
			resultado.setOk(false);
			resultado.setResultado(planet);
		} else {
			planetService.delete(planet);
			resultado.setResultado(planet);
			resultado.setMensagens(messages.get("form.planet.delete.ok"));
		}
		return new ResponseEntity<Resultado>(resultado, HttpStatus.OK);
	}

	@PostMapping("planets")
	@PreAuthorize("hasRole('NOLOGIN')")
	public ResponseEntity<Resultado> planets(@RequestBody Planet planet) {
		Resultado resultado = new Resultado();
		resultado.setOk(true);
		resultado.setResultado(planetService.getPlanets());
		resultado.setMensagens(null);
		return new ResponseEntity<Resultado>(resultado, HttpStatus.OK);
	}

}
