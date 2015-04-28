package foo.bar;

import foo.bar.external.HttpWeatherSource;
import foo.bar.foo.bar.domain.Observation;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.core.IsNot.not;

public class HttpObservationSourceTest {
    private HttpWeatherSource sut = new HttpWeatherSource("http://apis.is/weather/observations/en?stations=");

    @Test
    public void shallReturnValidCollectionOfWeatherResultsForStation1() throws Exception {
        Collection<Observation> observations = sut.getWeatherFor("1");

        assertThat(observations, notNullValue());
        assertThat(observations, not(emptyCollectionOf(Observation.class)));

    }

    @Test
    public void shallReturnEmptyJsonForNonExistingStation() throws Exception {
        Collection<Observation> nonExistingObservations = sut.getWeatherFor("_SADADSA");

        assertThat(nonExistingObservations, emptyCollectionOf(Observation.class));
    }

}
