require_relative "application"

weather_provider = WeatherAPIAdapter.new "http://apis.is/weather/observations/en?stations=1,36049"
output_provider = CSVFileProvider.new "output.csv"

puts "Starting application"

app = Application.new weather_provider, output_provider

app.process_weather

puts "Done."
