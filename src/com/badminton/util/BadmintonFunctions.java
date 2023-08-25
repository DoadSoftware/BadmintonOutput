package com.badminton.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.badminton.model.BadmintonApi;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BadmintonFunctions 
{
	public static BadmintonApi getBasketBallDatafromJson(String link) throws IOException 
	{
		URL url = new URL(link);
        URLConnection connection = url.openConnection();
        connection.connect();
        
        BadmintonApi my_data = new ObjectMapper().readValue(url, BadmintonApi.class);
        
		return my_data;
	}
}
