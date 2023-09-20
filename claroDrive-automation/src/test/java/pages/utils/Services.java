package pages.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import lib.ConsoleColors;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;


public class Services {
	
	public static JSONObject getJsonOfService( String url ){
		try {
			String tmpString = petitionHttpGet(url);
			
			JSONParser parser = new JSONParser();
			return (JSONObject) parser.parse( tmpString );	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static String petitionHttpGet(String url) throws Exception {
		StringBuilder str = new StringBuilder();
		URL tmpUrl = new URL(url);

		HttpURLConnection connection = (HttpURLConnection) tmpUrl.openConnection();
		connection.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line ;
		while ((  line = rd.readLine()) != null) {
			str.append( line );
		}
		rd.close();
		
		return str.toString();
	}
	
}
