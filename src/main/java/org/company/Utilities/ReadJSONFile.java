package org.company.Utilities;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSONFile {
    JSONParser parser = new JSONParser();

    private static File jsonfile;
    private static String name = "";

    public ReadJSONFile() {

    }

    public String getJsonValue(String JSONFileName, String key) {
        jsonfile = new File(System.getProperty("user.dir") + "\\src\\" + JSONFileName);

        try {

            Object obj = parser.parse(new FileReader(jsonfile));
            JSONObject jsonObject = (JSONObject) obj;
            name = (String) jsonObject.get(key);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static String getName() {
        return name;
    }
}




