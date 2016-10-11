package org.company.Utilities;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteJSONFile {
    JSONObject obj = new JSONObject();
    private static File jsonfile;

    public WriteJSONFile() {

    }

    public void writeValueJsonFile (String JSONFileName, String key, String value){
        jsonfile = new File(System.getProperty("user.dir")+"\\src\\"+JSONFileName);
        obj.put(key, value);

        try {

            FileWriter file = new FileWriter(jsonfile);
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);

    }

}
