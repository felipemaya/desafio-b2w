package com.swapi;

import com.swapi.models.Film;
import com.swapi.models.Planet;
import com.swapi.models.SWModelList;
import com.swapi.models.Vehicle;
import com.swapi.sw.StarWars;
import com.swapi.sw.StarWarsApi;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class SimpleTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private StarWars api;

    @Before
    public void setUp() {
        api = StarWarsApi.getApi();
    }

    @Test
    public void getAllFilms() throws Exception {
        Call<SWModelList<Film>> films = api.getAllFilms(1);

        Response<SWModelList<Film>> response = films.execute();
        if (response.isSuccessful()) {
            SWModelList<Film> data = response.body();
            int count = data.count;
            assertThat(count).isNotZero().isGreaterThan(0);
            for (Film film : data.results) {
                log.debug(String.format("Episode %d - Title: %-25s (%s)", film.episodeId, film.title, film.releaseDate));
            }
        }
        else {
            log.error(response.code() + " - " + response.message());
            fail("Request failed");
        }
    }

    @Test
    public void getAllPlanets() throws Exception {
        Call<SWModelList<Planet>> planets = api.getAllPlanets(null);

        Response<SWModelList<Planet>> response = planets.execute();
        if (response.isSuccessful()) {
            SWModelList<Planet> data = response.body();
            int count = data.count;
            assertThat(count).isNotZero().isGreaterThan(0);
            for (Planet planet : data.results) {
                log.debug(String.format("Name: %-25s Climate: %-25s Pop: %16s", planet.name, planet.climate, planet.population));
            }
        }
        else {
            log.error(response.code() + " - " + response.message());
            fail("Request failed");
        }
    }

    @Test
    public void getAllVehicles() throws Exception {
        Call<SWModelList<Vehicle>> vehicles = api.getAllVehicles(null);

        Response<SWModelList<Vehicle>> response = vehicles.execute();
        if (response.isSuccessful()) {
            SWModelList<Vehicle> data = response.body();
            int count = data.count;
            assertThat(count).isNotZero().isGreaterThan(0);
            for (Vehicle vehicle : data.results) {
                log.debug(String.format("Name: %-25s Model: %s by %s - Crew: %-3s",
                        vehicle.name, vehicle.model, vehicle.manufacturer, vehicle.crew));
            }
        }
        else {
            log.error(response.code() + " - " + response.message());
            fail("Request failed");
        }
    }
}
