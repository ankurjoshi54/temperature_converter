package org.example.temperatureconverter;

import io.dropwizard.Application;
import io.dropwizard.redis.RedisClientBundle;
import io.dropwizard.redis.RedisClientFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.lettuce.core.api.StatefulRedisConnection;

import org.example.temperatureconverter.caches.TemperatureCache;
import org.example.temperatureconverter.healthchecks.TemperatureConverterHealthCheck;
import org.example.temperatureconverter.resources.TemperatureConverterRes;

public class TemperatureConverterApp extends Application<TemperatureConverterConfig>{
    private final RedisClientBundle<String, String, TemperatureConverterConfig> redis =
            new RedisClientBundle<String, String, TemperatureConverterConfig>() {
                @Override
                public RedisClientFactory<String, String> getRedisClientFactory(
                        TemperatureConverterConfig configuration
                ) {
                    return configuration.getRedisClientFactory();
                }
            };

    public static void main(String[] args) throws Exception {
        new TemperatureConverterApp().run(args);
    }

    @Override
    public String getName() {
        return "temperature-converter";
    }

    @Override
    public void initialize(Bootstrap<TemperatureConverterConfig> bootstrap) {
        bootstrap.addBundle(redis);
    }

    @Override
    public void run(TemperatureConverterConfig configuration,
                    Environment environment) {
        final StatefulRedisConnection<String, String> connection = redis.getConnection();
        final TemperatureCache temperatureCache = new TemperatureCache(connection);
        final TemperatureConverterRes resource = new TemperatureConverterRes(
                configuration.getVersion(),
                temperatureCache
        );
        final TemperatureConverterHealthCheck temperatureConverterHealthCheck =
                new TemperatureConverterHealthCheck();
        // TODO: Debug why redis healthcheck aren't added by redis bundle.
        environment.healthChecks().register("temperature-converter-logic", temperatureConverterHealthCheck);
        System.out.println("environment = " + environment.healthChecks().getNames());
        environment.jersey().register(resource);
    }
}
