package com.example.demo.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.json.JSONException;
import org.json.JSONObject;

public class RestAPI {
	private static String cookie="";
	private static boolean session=false;
	
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	private static String saveCookie(HttpURLConnection conn) {
		Map<String, List<String>> imap = conn.getHeaderFields( ) ;
	    if( imap.containsKey( "Set-Cookie" ) )
	    {
	    	List<String> lString = imap.get( "Set-Cookie" ) ;
	    	System.out.println("list: "+ lString.toString());
	    	for( int i = 0 ; i < lString.size() ; i++ ) {
	    		System.out.println("lStringGet: " + lString.get(i));
	    		cookie += lString.get( i ) ;
	    	}
	    	session = true ;
	    } else {
	    	session = false ;
	    }
	    return null;
	}
	public static JSONObject readJsonFromUrl(String url, String data, String requestmethod, boolean auth) throws IOException, JSONException {
		URL t_url = new URL(url);

		HttpURLConnection conn = (HttpURLConnection)t_url.openConnection();
		
		System.out.println("\n==================Start===================");
		System.out.println("RequestURL: "+url);
		System.out.println("data: " + data);
		System.out.println("method: " + requestmethod);
		System.out.println("auth: " + auth);
		System.out.println("currentCookie: " + cookie);
		
		// Request Method
		conn.setRequestMethod(requestmethod);
		
		// Request Property
		conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
		
		conn.setRequestProperty("Cookie", cookie);
		
		// Input & Output 
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(data == null? false : true);
		
		
		
		// Connect
		conn.connect();
		
		// if data is not null
		if(data != null) {
			System.out.println("data쓰기");
			byte[] dataBytes = data.getBytes();
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream()); 
			wr.write(dataBytes); 
			wr.flush(); 
			wr.close();
		}
		
		
		//cookie example
//		System.out.println("cookie test");
//		System.out.println(conn.getHeaderField("set-cookie"));
//		HashMap<String, String> testcookie = new HashMap<String, String>();
//		for(String str: conn.getHeaderField("set-cookie").split(";\\s")) {
//			try {
//				System.out.println(str.split("=")[0]+"=="+str.split("=")[1]);
//				testcookie.put(str.split("=")[0], str.split("=")[1]);
//			} catch (Exception e) {
//				System.out.println(str);
//				testcookie.put(str, str);
//			}
//			
//		}
//		System.out.println("★"+testcookie.toString());
		
		//end cookie example
		
		
		
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String jsonText = readAll(rd);
			
			JSONObject json = new JSONObject(jsonText);
			
			if(auth == true) cookie = json.getJSONObject("session").get("name")
					+"="
					+json.getJSONObject("session").get("value")
					+";";
			
			
			System.out.println("==================END=====================");
			return json;
		} finally {
	    }
	 }
	
	public static String getCookie() {
		return cookie;
	}
	
	public static void main(String[] args) throws JSONException, IOException {
		JSONObject json = null;
		String data = "{"
  		+ "\n\"username\":\"test\", "
  		+ "\n\"password\":\"1234\"  "
  		+ "\n}";
		
		
		
		json = readJsonFromUrl("http://localhost:8080/rest/auth/1/session", data, "POST", true);
		System.out.println("결과: " + json.toString());
		
		
		json = readJsonFromUrl("http://localhost:8080/rest/api/2/issue/HAP-10", null, "GET", false);
		System.out.println("결과: " + json.toString());
		
		json = readJsonFromUrl("http://localhost:8080/rest/api/2/issue/HAP-10", null, "GET", false);
		System.out.println("결과: " + json.toString());
		  
		  
		  
	}
	



}
