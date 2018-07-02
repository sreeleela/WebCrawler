package com.project.urlmanager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndPoint {
	
	private List<String> pages;
	
	public void getUrlsFromEndPoint(String endPoint) {
		final Logger logger = LoggerFactory.getLogger(EndPoint.class);
        try {         
        	logger.info("Reading json from start point.");
        	
        	JSONObject json = new JSONObject(IOUtils.
        			toString(new URL(endPoint), Charset.forName("UTF-8")));
        	logger.info("Read json.");
        	
        	String links[] = json.getString("links").replaceAll("\"", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
        	pages = Arrays.asList(links);
        	UrlsStack.pagesToVisit.addAll(pages);
        	
        	logger.info("Initial pages to visit information updated.");
        	
        	for(int i=0;i<UrlsStack.getPagesToVisit().size();i++) {
        		System.out.println(UrlsStack.getPagesToVisit().get(i));
        	}
        	
        } catch (JSONException e) {
			logger.error("JSON Exception, while reading start point urls: "+e.getMessage());
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException, while reading start point urls: "+e.getMessage());
		} catch (IOException e) {
			logger.error("IOException, while reading start point urls: "+e.getMessage());
		}   
	}
}
