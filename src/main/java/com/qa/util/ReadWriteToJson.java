package com.qa.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadWriteToJson {

	String baseLocation = System.getProperty("user.dir");
	String writeLocation = baseLocation+"/src/main/java/com/qa/optput/";
	String readLocation = baseLocation+"/src/main/java/com/qa/input/";
	
	JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject;
	
	public void writeToJsonFile(String filename,String response) {
		try {
			FileWriter file = new FileWriter(writeLocation+filename);
			file.write(response);
			file.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	public JSONObject readFromJsonFile(String filename)  {
	    try(FileReader reader = new FileReader(readLocation + filename)) {
		    Object obj = jsonParser.parse(reader);
		    jsonObject = (JSONObject)obj;
	        return jsonObject;
        } catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
  }
}
