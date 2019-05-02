package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//1.GET Method
	public void get(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		try {
			//Get status code
			httpClient.execute(httpGet);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("Status Code: -----"+statusCode);
			
			//Json response Object
			String data = EntityUtils.toString(response.getEntity(),"UTF-8");
			JSONObject responseJson = new JSONObject(data);
			System.out.println("Response in JSON:-----" +responseJson);
			
			// Response Headers
			Header[] headersArray =  response.getAllHeaders();
			HashMap<String,String> allHeaders = new HashMap<>();
			
			for(Header header : headersArray) {
				allHeaders.put(header.getName(), header.getValue());
			}
			
			System.out.println("All response headers:-----"+ allHeaders);
		} catch (ClientProtocolException e) {
	
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
	}

}
