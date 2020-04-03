package Services;
import Controllers.JSONParsers.CountryJSONparser;
import Controllers.JSONParsers.CurrencyJsonParser;
import Controllers.JSONParsers.NBPJsonParser;
import Controllers.JSONParsers.WeatherJsonParser;
import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Service {
    private final String WEATHER_API_KEY= "3781566920336af86daec0e4b7ecfdae";
    private String country;
    private String cuntryCurrencyCode;
    public Service(String country){
        this.country=country;
    }
    public Service(){}

   private String getDataFromURL(String url) throws IOException {
        URL objectURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) objectURL.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if(responseCode != 200) {
            System.out.print("Nie udało się połączyć z serverem! Błąd: ");
            System.out.println(responseCode);
            return null;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder reponose = new StringBuilder();
        while((line=in.readLine())!=null){
            reponose.append(line);
        }
        in.close();
        return reponose.toString();
    }


    private String makeWeatherURL(String city){
//       this.city = controller.getCity();
       return String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s",city, WEATHER_API_KEY);
    }
    private String makeCountryURL(){
        return String.format("https://restcountries.eu/rest/v2/name/%s?fullText=true", country);
    }
    private String makeNBPRatioURL(String currencyCode){
        return String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/?format=json", currencyCode);
    }
    private String makeRatioForURL(String currencyCode){
        return String.format("https://api.exchangeratesapi.io/latest?base=%s&symbols=%s", cuntryCurrencyCode, currencyCode);
    }

    public void getCountry() throws IOException {
        CountryJSONparser countryJSON = new CountryJSONparser(new JSONArray((getDataFromURL(makeCountryURL()))));
        cuntryCurrencyCode = countryJSON.getCountryCode();
    }
    public String getRatioFor(String currencyCode) throws IOException {
        if(cuntryCurrencyCode.isEmpty())
            getCountry();
        String JSONdata = getDataFromURL(makeRatioForURL(currencyCode));
        CurrencyJsonParser currencyParser = new CurrencyJsonParser(JSONdata, cuntryCurrencyCode, currencyCode);
        return currencyParser.toString();
    }
    public String getNBPRadio() throws IOException {
        getCountry();
        if(cuntryCurrencyCode.equals("PLN")){
            return "PLN to PLN = 1";
        }
        String url =makeNBPRatioURL(cuntryCurrencyCode);
        NBPJsonParser nbpJsonParser = new NBPJsonParser(new JSONObject(getDataFromURL(url)));
        return  String.format("%s %s%n", cuntryCurrencyCode, nbpJsonParser);
    }
    public String getWeather(String city) throws IOException {
        if(city.isEmpty())
            return "Nie podano miasta!";

        String url = makeWeatherURL(city);
        WeatherJsonParser weather = new WeatherJsonParser(new JSONObject(getDataFromURL(url)));
        return weather.toString();
    }
}
