package org.example.temperatureconverter.resources;

import org.example.temperatureconverter.caches.TemperatureCache;
import org.example.temperatureconverter.serializers.Temperature;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/convert")
@Produces(MediaType.APPLICATION_JSON)
public class TemperatureConverterRes {
    private final String version;
    private final TemperatureCache temperatureCache;

    public TemperatureConverterRes(String version, TemperatureCache temperatureCache) {
        this.version = version;
        this.temperatureCache = temperatureCache;
    }

    @GET
    @Timed
    public Temperature convertTemperature(@QueryParam("unit") Optional<Double> tempUnit) {
        return tempUnit
                .map(aDouble -> new Temperature("Celsius", aDouble, version, temperatureCache))
                .orElse(null);
    }
}
