package com.swapi.sw;

import com.swapi.models.Film;
import com.swapi.models.People;
import com.swapi.models.Planet;
import com.swapi.models.Root;
import com.swapi.models.SWModelList;
import com.swapi.models.Species;
import com.swapi.models.Starship;
import com.swapi.models.Vehicle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Oleur on 21/12/2014.
 * Star Wars API interface for making calls.
 */
public interface StarWars {

    @GET("")
    Call<Root> getRootUrls();

    @GET("people/")
    Call<SWModelList<People>> getAllPeople(@Query("page") int page);

    @GET("people/{id}/")
    Call<People> getPeople(@Path("id") int peopleId);

    @GET("films/")
    Call<SWModelList<Film>> getAllFilms(@Query("page") int page);

    @GET("films/{id}/")
    Call<Film> getFilm(@Path("id") int filmId);

    @GET("starships")
    Call<SWModelList<Starship>> getAllStarships(@Query("page") int page);

    @GET("starships/{id}/")
    Call<Starship> getStarship(@Path("id") int starshipId);

    @GET("vehicles/")
    Call<SWModelList<Vehicle>> getAllVehicles(@Query("page") Integer page);

    @GET("vehicles/{id}/")
    Call<Vehicle> getVehicle(@Path("id") int vehicleId);

    @GET("species/")
    Call<SWModelList<Species>> getAllSpecies(@Query("page") int page);

    @GET("species/{id}/")
    Call<Species> getSpecies(@Path("id") int speciesId);

    @GET("planets/")
    Call<SWModelList<Planet>> getAllPlanets(@Query("page") Integer page);

    @GET("planets/{id}/")
    Call<Planet> getPlanet(@Path("id") int planetId);

}
