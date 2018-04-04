package com.udolf.thrillio.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpConnect {
public static String download(String sourceURL) throws MalformedURLException, URISyntaxException{
	System.out.println("Downloading "+sourceURL);
	URL url=new URI(sourceURL).toURL();
	
		HttpURLConnection con;
		try {
			con = (HttpURLConnection)url.openConnection();
			int responseCode=con.getResponseCode();
			if(responseCode>=200&&responseCode<300){
				return IOUtil.read(con.getInputStream());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	


}
}
