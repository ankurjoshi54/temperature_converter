package org.example.temperatureconverter.caches;

import io.lettuce.core.RedisException;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.Optional;

public class TemperatureCache {
    private final RedisCommands<String, String> sync;

    public TemperatureCache(StatefulRedisConnection<String, String> redisConnection) {
        this.sync = redisConnection.sync();
    }

    public Optional<Double> getTemperature(Double fahrenheit) {
        Optional<String> celsius;
        try {
            celsius = Optional.ofNullable(this.sync.get(fahrenheit.toString()));
        } catch (RedisException e) {
            // Add error log here.
            return Optional.empty();
        }
        return celsius.map(Double::parseDouble);
    }

    public void setTemperature(Double fahrenheit, Double celsius) {
        try {
            this.sync.set(fahrenheit.toString(), celsius.toString());
        } catch (RedisException e) {
            // Add error log here.
        }
    }
}
