package org.example.temperatureconverter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.redis.RedisClientFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TemperatureConverterConfig extends Configuration {
    @NotEmpty
    private String version;

    @Valid
    @NotNull
    @JsonProperty("redis")
    private RedisClientFactory<String, String> redisClientFactory;

    @JsonProperty
    public String getVersion() {
        return version;
    }

    @JsonProperty
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("redis")
    public RedisClientFactory<String, String> getRedisClientFactory() {
        return this.redisClientFactory;
    }

    @JsonProperty("redis")
    public void setRedisClientFactory(RedisClientFactory<String, String> redisClientFactory) {
        this.redisClientFactory = redisClientFactory;
    }
}
