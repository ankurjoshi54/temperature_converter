package org.example.temperatureconverter.serializers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.temperatureconverter.caches.TemperatureCache;

public class Temperature {
    private String unit;
    private Double value;
    private String version;
    private TemperatureCache cache;

    public Temperature() {
        // Needed by Jackson deserialization
    }

    public Temperature(String unit, Double value, String version, TemperatureCache cache) {
        this.unit = unit;
        this.value = value;
        this.version = version;
        this.cache = cache;
    }

    @JsonProperty
    public String getUnit() {
        return unit;
    }

    @JsonProperty
    public Double getValue() {
        return this.cache.getTemperature(this.value).orElseGet(() -> toCelsius(this.value));
    }

    @JsonProperty
    public String getVersion() {
        return version;
    }

    private Double toCelsius(Double fahrenheit) {
        // TODO: Store value in such a way as to not overload the server with temperature values
        System.out.println("Value not present in cache, processing value: " + fahrenheit);
        Double celsius = ((fahrenheit - 32) * 5) / 9;
        this.cache.setTemperature(fahrenheit, celsius);
        return celsius;
    }
}
