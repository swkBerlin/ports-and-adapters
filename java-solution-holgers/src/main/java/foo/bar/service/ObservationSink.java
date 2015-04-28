package foo.bar.service;


import java.util.Collection;

public interface ObservationSink {
    void storeObservations(String observationName, Collection<String> exportOutput);
}
