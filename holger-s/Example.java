public class APIAccess {

    public static void main(String[] args) {
        try {
            String url = "http://apis.is/weather/observations/en?stations=1";
            URL url = new URL(fullURL);
            try (InputStream inputStream = url.openStream();
                 InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                         Charset.forName
                                 ("UTF-8"));

            ) {
                Gson gson = new Gson();
                return gson.fromJson(inputStreamReader, Map.class).getResults();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("unable to parse URL", e);
        } catch (IOException e) {
            throw new RuntimeException("unable to readData", e);
        }
    }
}