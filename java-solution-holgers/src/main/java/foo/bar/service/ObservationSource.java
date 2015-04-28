package foo.bar.service;

import foo.bar.foo.bar.domain.Observation;

import java.util.Collection;

public interface ObservationSource {
    Collection<Observation> getWeatherFor(String... stationIds);
}
