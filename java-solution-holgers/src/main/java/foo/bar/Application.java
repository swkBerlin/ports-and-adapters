package foo.bar;


import foo.bar.foo.bar.domain.Observation;
import foo.bar.service.CvsMapper;
import foo.bar.service.ObservationSink;
import foo.bar.service.ObservationSource;

import java.util.Collection;
import java.util.LinkedList;

public class Application {
    private final ObservationSource observationSource;
    private final ObservationSink observationSink;
    private final CvsMapper cvsMapper;

    public Application(ObservationSource observationSource, ObservationSink observationSink) {
        this.observationSource = observationSource;
        this.observationSink = observationSink;
        this.cvsMapper = new CvsMapper();
    }

    public void exportFor(String stationId) {
        Collection<String> result = new LinkedList<>();
        Collection<Observation> observations = observationSource.getWeatherFor(stationId);
        for (Observation observation : observations) {
            String csvRow = cvsMapper.map(observation);
            result.add(csvRow);
        }
        observationSink.storeObservations(stationId, result);
    }
}
