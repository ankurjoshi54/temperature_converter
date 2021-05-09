package org.example.temperatureconverter.services.converter;

import org.example.temperatureconverter.caches.TemperatureCache;

public class TemperatureConverter {
    public static Double getCelsius(Double fahrenheit, TemperatureCache cache) {
        if (null == cache) return toCelsius(fahrenheit, null);

        return cache.getTemperature(fahrenheit).orElseGet(() -> toCelsius(fahrenheit, cache));
    }

    private static Double toCelsius(Double fahrenheit, TemperatureCache cache) {
        // TODO: Store value in such a way as to not overload the server with temperature values
        Double celsius = ((fahrenheit - 32) * 5) / 9;
        if (cache != null) {
            System.out.println("Value not present in cache, processing value: " + fahrenheit);
            cache.setTemperature(fahrenheit, celsius);
        }

        return celsius;
    }
}
