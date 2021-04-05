package org.com.demo.pkalita.util;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static JsonPath stringToJson(String response){
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }

    public static String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path))
        );
    }

    public static String getJsonPath(Response response, String key){
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        return jsonPath.get(key).toString();
    }
}
