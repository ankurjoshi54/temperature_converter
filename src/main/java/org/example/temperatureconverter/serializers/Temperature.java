package org.example.temperatureconverter.serializers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.temperatureconverter.caches.TemperatureCache;
import org.example.temperatureconverter.services.converter.TemperatureConverter;

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
        return TemperatureConverter.getCelsius(this.value, this.cache);
    }

    @JsonProperty
    public String getVersion() {
        return version;
    }
}
