package com.swapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Oleur on 22/12/2014.
 * Starship model represents a single transport craft that has hyperdrive capability.
 */
public class Starship extends Vehicle implements Serializable {

    @JsonProperty("starship_class")
    public String starshipClass;

    @JsonProperty("hyperdrive_rating")
    public String hyperdriveRating;

    @JsonProperty("MGLT")
    public String mglt;

}
