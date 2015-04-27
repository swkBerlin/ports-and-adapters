require "spec_helper"

describe Application do

  let(:weather_data) {
    described_class::WeatherInformation.new "id",
                                            "name",
                                            Date.today,
                                            Time.now,
                                            "20.0",
                                            "1019",
                                            "NE"
  }

  let(:weather_provider) {
      provider = double("weather_provider")
      allow(provider).to receive(:information).and_return weather_data
      provider
  }

  let(:output_provider) {
    writer = double("output_provider")
    allow(writer).to receive(:write).and_return true
    writer
  }

  subject { described_class.new weather_provider, output_provider }

  describe ".process_weather" do

    it "fetches the weather from the weather_provider and hands it to the output provider" do
      expect(output_provider).to receive(:write).with weather_data
      subject.process_weather
    end

  end

end


describe WeatherAPIAdapter do

  describe ".information" do

    it "calls the API and returns its json data" do
      expect(subject.information.size).to be 1
    end

  end

end

describe CSVFileProvider do

  # TODO write CSV file provider

end
