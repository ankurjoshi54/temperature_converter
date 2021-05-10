package org.example.temperatureconverter.serializers;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TemperatureTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    @DisplayName("Temperature is serialized to JSON correctly")
    public void serializesToJSON() throws Exception {
        final Temperature temperature = new Temperature("Celsius", -11.53, "0.0.1", null);
        final String expected = MAPPER.writeValueAsString(
            MAPPER.readValue(fixture("fixtures/temperature.json"), Temperature.class));

        assertThat(MAPPER.writeValueAsString(temperature)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Temperature temperature = new Temperature("Celsius", -11.53, "0.0.1", null);
        assertThat(MAPPER.readValue(fixture("fixtures/temperature.json"), Temperature.class))
            .usingRecursiveComparison().isEqualTo(temperature);
    }
}
