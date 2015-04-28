package foo.bar;

import foo.bar.foo.bar.domain.Observation;
import foo.bar.service.ObservationSink;
import foo.bar.service.ObservationSource;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApplicationShallExportObservationsTest {
    private ObservationSource observationSource = Mockito.mock(ObservationSource.class);
    private ObservationSink observationSink = Mockito.mock(ObservationSink.class);
    private List<Observation> observationsFound = new LinkedList<>();
    private Application sut;

    //TODO maybe can use ParametrizedTests of Junit

    @Before
    public void setUp() throws Exception {
        sut = new Application(observationSource, observationSink);
        when(observationSource.getWeatherFor(anyString())).thenReturn(observationsFound);
    }

    @Test
    public void shouldExportCSVRowForStation1() throws Exception {
        observationsFound.add(new Observation(1, "name", "time", null));

        sut.exportFor("1");

        verify(observationSink).storeObservations(eq("1"), containsInAnyOrder("1,name,time"));
    }

    //add more tests for more than one station


    private Collection<String> containsInAnyOrder(String... cvsRows) {
        return (Collection<String>) org.mockito.Matchers.argThat(Matchers
                .containsInAnyOrder(cvsRows));
    }
}
