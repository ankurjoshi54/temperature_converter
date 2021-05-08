package org.example.temperatureconverter.healthchecks;

import com.codahale.metrics.health.HealthCheck;

public class RedisHealthCheck extends HealthCheck {
    @Override
    protected Result check() {
        return Result.healthy();
    }
}
