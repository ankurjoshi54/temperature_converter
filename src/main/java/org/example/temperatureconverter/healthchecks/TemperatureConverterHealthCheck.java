package org.example.temperatureconverter.healthchecks;

import com.codahale.metrics.health.HealthCheck;
import org.example.temperatureconverter.serializers.Temperature;

public class TemperatureConverterHealthCheck extends HealthCheck {
    @Override
    protected Result check() {
        Temperature temperature = new Temperature("Celsius", -40.0, "0.0.1", null);
        if (temperature.getValue() == -40.0) {
            return Result.healthy();
        } else {
            return Result.unhealthy("Temperature converter not working");
        }
    }
}
