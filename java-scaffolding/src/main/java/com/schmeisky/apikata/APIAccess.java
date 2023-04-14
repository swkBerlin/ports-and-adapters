package com.schmeisky.apikata;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;

import java.nio.charset.Charset;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class APIAccess {

    public static void main(final String[] args) {
        try {
            final URL url = new URL("https://apis.is/weather/observations/en?stations=1");
            try(InputStream inputStream = url.openStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            ) {
                final Gson gson = new Gson();
                final Result result = gson.fromJson(inputStreamReader, Result.class);
                System.out.println(result);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("unable to parse URL", e);
        } catch (IOException e) {
            throw new RuntimeException("unable to readData", e);
        }
    }

    public static class Result {

        List<Map<String, Object>> results;

        public Result(final List<Map<String, Object>> results) {
            this.results = results;
        }

        public List<Map<String, Object>> getResults() {
            return results;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "results=" + results +
                    '}';
        }
    }
}
