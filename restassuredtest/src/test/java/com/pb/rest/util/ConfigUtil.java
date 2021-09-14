package com.pb.rest.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
private static ConfigUtil manager;
private static final Properties prop = new Properties();

private ConfigUtil() throws Exception{
	InputStream inputStream = ConfigUtil.class.getResourceAsStream("../src/test/resource/prod.properties");
    prop.load(inputStream);
}

public static ConfigUtil getInstance(){
	if(manager == null){
		synchronized (ConfigUtil.class){
			try {
				manager = new ConfigUtil();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	return manager;
}
public String getString(String key){
	return System.getProperty(key, prop.getProperty(key));
}



}
