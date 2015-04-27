require_relative "application"

weather_provider = WeatherAPIAdapter.new
output_provider = CSVFileProvider.new

puts "Starting application"

app = Application.new weather_provider, output_provider

app.process_weather

puts "Done."
