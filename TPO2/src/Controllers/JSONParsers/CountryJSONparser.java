package Controllers.JSONParsers;

import org.json.JSONArray;
import org.json.JSONObject;

public class CountryJSONparser {
    private String countryCode;
    public CountryJSONparser(JSONArray data){
        prepareData(data);
    }
    private void prepareData(JSONArray data){
        JSONArray array = data.getJSONObject(0).getJSONArray("currencies");
        countryCode = array.getJSONObject(0).get("code").toString();
    }

    public String getCountryCode() {
        return countryCode;
    }
}
