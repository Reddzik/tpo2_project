package Controllers.JSONParsers;

import org.json.JSONObject;

public class CurrencyJsonParser {
    private double ratioToEqual;
    private double countryRatio;
    private String countryCurrencyCode;
    private String currecnyCodeToEqual;

    public CurrencyJsonParser(String data, String countryCurrencyCode, String currecnyCodeToEqual){
        this.countryCurrencyCode= countryCurrencyCode;
        this.currecnyCodeToEqual= currecnyCodeToEqual;
    prepareData(data, countryCurrencyCode, currecnyCodeToEqual);
    }

    private void prepareData(String data, String countryCurrencyCode, String currecnyCodeToEqual){
        JSONObject dataJSON = new JSONObject(data).getJSONObject("rates");
        ratioToEqual = dataJSON.getDouble(currecnyCodeToEqual);
    }

    @Override
    public String toString() {
        return String.format(" kurs %s do waluty %s wynosi %s", countryCurrencyCode, currecnyCodeToEqual, ratioToEqual);
    }
}
