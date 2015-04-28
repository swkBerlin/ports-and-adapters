package foo.bar.external;

import com.google.gson.Gson;
import foo.bar.foo.bar.domain.Observation;
import foo.bar.service.ObservationSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class HttpWeatherSource implements ObservationSource {
    private final String urlFormat;

    public HttpWeatherSource(String urlPrefix) {
        this.urlFormat = urlPrefix + "%s";
    }

    @Override
    public Collection<Observation> getWeatherFor(String... stationIds) {
        try {
            String fullURL = String.format(urlFormat, Arrays.stream(stationIds).collect(Collectors.joining(",")));
            URL url = new URL(fullURL);
            try (InputStream inputStream = url.openStream();
                 InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                         Charset.forName
                                 ("UTF-8"));

            ) {
                Gson gson = new Gson();
                return gson.fromJson(inputStreamReader, Result.class).getResults();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("unable to parse URL", e);
        } catch (IOException e) {
            throw new RuntimeException("unable to readData", e);
        }
    }

    public static class Result {

        List<Observation> results;

        public Result(List<Observation> results) {
            this.results = results;
        }

        public List<Observation> getResults() {
            return results;
        }
    }
}
