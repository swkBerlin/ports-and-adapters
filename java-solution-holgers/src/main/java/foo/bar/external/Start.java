package foo.bar.external;

import foo.bar.Application;

public class Start {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Please provide station id as command line argument");

        } else {
            exportFor(args[0]);
        }
    }

    private static void exportFor(String stationId) {
        HttpWeatherSource httpWeatherSource = new HttpWeatherSource("http://apis.is/weather/observations/en?stations=");
        FileWeatherSink fileWeatherSink = new FileWeatherSink();
        Application application = new Application(httpWeatherSource, fileWeatherSink);
        application.exportFor(stationId);
        System.out.println("DONE");
    }
}
