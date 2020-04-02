package Controllers;


import Services.Service;

import java.io.IOException;

public class GUIController {
    private StringBuilder textField = new StringBuilder();
    private String wikiPageURL;
    public void handleCityClick(String city) throws IOException {
        Service service = new Service();
        textField.append(service.getWeather(city));
        textField.append("\n");
        wikiPageURL = String.format("https://en.wikipedia.org/wiki/%s", city);
    }
    public String handleTextChange(){
        return textField.toString();
    }
    public void handleCountryClick(String country, String currencyChoosed) throws IOException {
        Service service = new Service(country);
        textField.append(service.getNBPRadio());
        textField.append("\n");
        textField.append(service.getRatioFor(currencyChoosed));
        textField.append("\n");
    }

    public String getWikiPageURL() {
        return wikiPageURL;
    }

}
