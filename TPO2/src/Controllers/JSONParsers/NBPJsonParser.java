package Controllers.JSONParsers;

import org.json.JSONArray;
import org.json.JSONObject;

public class NBPJsonParser {
    private double ratioToPLN;

    public NBPJsonParser(JSONObject data){
        prepareDateToDisplay(data);
    }
    private void prepareDateToDisplay(JSONObject data){
        JSONArray array = data.getJSONArray("rates");
        JSONObject ratesData = new JSONObject(array.getJSONObject(0).toString());
        ratioToPLN = ratesData.getDouble("mid");
    }

    public double getRatioToPLN() {
        return ratioToPLN;
    }

    @Override
    public String toString() {
        return String.format("Ratio to PLN:%s", getRatioToPLN());
    }
}
