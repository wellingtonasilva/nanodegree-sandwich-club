package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.optJSONObject(NAME);

            return new Sandwich(name.optString(MAIN_NAME),
                    from(name.optJSONArray(ALSO_KNOWN_AS)),
                    jsonObject.optString(PLACE_OF_ORIGIN),
                    jsonObject.optString(DESCRIPTION),
                    jsonObject.optString(IMAGE),
                    from(jsonObject.optJSONArray(INGREDIENTS)));
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
    private static List<String> from(JSONArray jsonArray) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(jsonArray.optString(i));
        }
        return result;
    }
}
