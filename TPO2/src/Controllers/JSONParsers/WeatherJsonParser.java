package Controllers.JSONParsers;

import org.json.JSONObject;

import java.util.List;

public class WeatherJsonParser {
    private String city;
    private double temp;
    private double feelsLike;


    public WeatherJsonParser(JSONObject dataAboutCity){
        prepareDataToDisplay(dataAboutCity);
    }

    public void prepareDataToDisplay(JSONObject data) {
        city = data.getString("name");
        JSONObject main_obj = new JSONObject(data.getJSONObject("main").toString());
        temp = main_obj.getDouble("temp");
        feelsLike = main_obj.getDouble("feels_like");
    }

    public String getCity() {
        return city;
    }

    public double getTemp() {
        return temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    @Override
    public String toString() {
        return String.format("Pogoda w mieście %s: \nTemperatura: %s °C\nOczuwalna temperatura: %s °C", city, temp, feelsLike);
    }
}
