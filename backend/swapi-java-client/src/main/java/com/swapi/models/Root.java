package com.swapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Oleur on 21/12/2014.
 * Root model that provides information on all avaiable resources within the API.
 */
public class Root implements Serializable {
    @JsonProperty("films")
    public String filmsUrl;
    @JsonProperty("people")
    public String peopleUrl;
    @JsonProperty("planets")
    public String planetsUrl;
    @JsonProperty("species")
    public String speciesUrl;
    @JsonProperty("starships")
    public String starshipsUrl;
    @JsonProperty("vehicles")
    public String vehiclesUrl;
}
