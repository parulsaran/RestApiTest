package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBaseProperty {
	Properties prop;
    
	public TestBaseProperty() {
	  prop = new Properties();

	  try {
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
		prop.load(ip);
		}catch(FileNotFoundException ex) {
		ex.printStackTrace();
		}catch(IOException e) {
		e.printStackTrace();

      }
   }
}