package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject("name");

            return new Sandwich(name.getString("mainName"),
                    from(name.getJSONArray("alsoKnownAs")),
                    jsonObject.getString("placeOfOrigin"),
                    jsonObject.getString("description"),
                    jsonObject.getString("image"),
                    from(jsonObject.getJSONArray("ingredients")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert an JSONArray into List of String
     * @param jsonArray
     * @return List of String
     * @throws JSONException
     */
    private static List<String> from(JSONArray jsonArray) throws JSONException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(jsonArray.getString(i));
        }
        return result;
    }
}
