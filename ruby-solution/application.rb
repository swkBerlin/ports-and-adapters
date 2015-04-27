class Application

  # Output Ports
  attr_reader :weather_provider
  attr_reader :output_provider

  def initialize(weather_provider, output_provider)
    @weather_provider = weather_provider
    @output_provider = output_provider
  end

  def process_weather
    @output_provider.write @weather_provider.information
  end

  WeatherInformation = Struct.new :id, :name, :date, :time, :temperature, :pressure, :wind_direction
end


require "open-uri"
require "json"

class WeatherAPIAdapter # implements WeatherProvider

  #
  # @return [Application::WeatherInformation]
  #
  def information
    # uri could be configured in constructor
    uri = URI.parse "http://apis.is/weather/observations/en?stations=1"

    content = uri.read

    json = JSON.parse content

    information = []

    json["results"].each { |result|
      info = Application::WeatherInformation.new result["id"],
                                                 result["name"],
                                                 DateTime.parse(result["time"]).to_date,
                                                 DateTime.parse(result["time"]).to_time,
                                                 result["T"],
                                                 result["P"],
                                                 result["D"]

      information << info
    }

    information
  end

end


require "csv"

class CSVFileProvider

  def write(information)
    # target file could be configured in constructor
    target = "./output.csv"

    CSV.open(target, "w") { |csv|

      header_created = false

      # For simplicity we skip the step of parsing
      information.each { |info|

        unless header_created
          csv << info.members.to_a
          header_created = true
        end

        csv << info.values.to_a
      }

    }
  end

end
