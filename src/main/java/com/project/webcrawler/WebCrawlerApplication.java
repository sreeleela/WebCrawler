package com.project.webcrawler;

import com.project.urlmanager.UrlsStack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.project.urlmanager.EndPoint;

/**
 * Main class
 * @author Sree
 *
 */
public class WebCrawlerApplication {  
	
	static Logger logger = LoggerFactory.getLogger(WebCrawlerApplication.class);

	
    public static void main( String[] args){
    	
    	String endPoint = "https://raw.githubusercontent.com/OnAssignment/compass-interview/master/data.json";
    	
    	EndPoint endPointURL = new EndPoint();
    	endPointURL.getUrlsFromEndPoint(endPoint);
    	
    	UrlsStack.search(UrlsStack.pagesToVisit.get(0));
    	
    	logger.info("Web Crawling Done!!");
    }

}