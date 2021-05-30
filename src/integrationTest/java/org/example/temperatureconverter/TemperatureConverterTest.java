package org.example.temperatureconverter;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.eclipse.jetty.http.HttpStatus;
import org.example.temperatureconverter.TemperatureConverterApp;
import org.example.temperatureconverter.TemperatureConverterConfig;
import org.example.temperatureconverter.serializers.Temperature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class TemperatureConverterTest {
    private static DropwizardAppExtension<TemperatureConverterConfig> EXT = new DropwizardAppExtension<>(
        TemperatureConverterApp.class,
        ResourceHelpers.resourceFilePath("config/temperature_converter.yaml")
    );

    @Test
    void getSuccessResponseOnConversion() {
        Client client = EXT.client();
        Response response = client.target(
            String.format("http://localhost:%d/convert", EXT.getLocalPort()))
            .queryParam("unit", 11.25)
            .request().get();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
    }

    @Test
    void getCelsiusConvertedTemperature() {
        Client client = EXT.client();
        Temperature temperature = client.target(
            String.format("http://localhost:%d/convert", EXT.getLocalPort()))
            .queryParam("unit", 11.25)
            .request().get(Temperature.class);
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(temperature.getUnit()).isEqualTo("Celsius");
        softly.assertThat(temperature.getValue()).isEqualTo(-24.182098765432098);
        softly.assertThat(temperature.getVersion()).isEqualTo("0.0.1");

        softly.assertAll();
    }
}
