package com.qa.framework.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonDataReader {

    public static Object[][] getInvalidLoginData() {

        try {
            String content = new String(
                    Files.readAllBytes(
                            Paths.get("src/test/resources/testdata/invalidLoginData.json")
                    )
            );

            JSONArray jsonArray = new JSONArray(content);

            Object[][] data = new Object[jsonArray.length()][3];

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                data[i][0] = obj.getString("username");
                data[i][1] = obj.getString("password");
                data[i][2] = obj.getString("expectedError");
            }

            return data;

        } catch (IOException e) {
            throw new RuntimeException("Failed to read test data file");
        }
    }
}