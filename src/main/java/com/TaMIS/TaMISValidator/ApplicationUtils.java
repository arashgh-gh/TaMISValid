package com.TaMIS.TaMISValidator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ApplicationUtils {

    public static JSONObject parseInputStreamToJsonObject(InputStream is) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String newLine;
        StringBuilder content = new StringBuilder();

        while ((newLine = in.readLine()) != null) {
            content.append(newLine);
            content.append(System.lineSeparator());

        }

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(String.valueOf(content));

        return jsonObject;
    }

}
