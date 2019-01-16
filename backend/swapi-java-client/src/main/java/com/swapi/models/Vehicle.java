package com.swapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Oleur on 22/12/2014.
 * Vehicle model represents a single transport craft that does not have hyperdrive capability.
 */
public class Vehicle implements Serializable {
    public String name;
    public String model;

    @JsonProperty("vehicle_class")
    public String vehicleClass;

    public String manufacturer;

    @JsonProperty("cost_in_credits")
    public String costInCredits;

    public String length;
    public String crew;
    public String passengers;

    @JsonProperty("max_atmosphering_speed")
    public String maxAtmospheringSpeed;

    @JsonProperty("cargo_capacity")
    public String cargoCapacity;

    public String consumables;
    public DateTime created;
    public DateTime edited;
    public String url;

    @JsonProperty("pilots")
    public List<String> pilotsUrls;

    @JsonProperty("films")
    public List<String> filmsUrls;
}
