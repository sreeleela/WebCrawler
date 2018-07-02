package com.project.webcrawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.urlmanager.UrlsStack;

public class Crawler {
	
	// We'll use a fake USER_AGENT so the web server thinks the robot is a normal web browser.
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<String>();
    final Logger logger = LoggerFactory.getLogger(Crawler.class);

    public boolean crawl(String url){
    	UrlsStack.totalHTTPRequests = UrlsStack.totalHTTPRequests + 1;
    	Connection connection = null;
    	//logger.info("Visiting " + url);
        try{
            connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            if(connection.response().statusCode() == (200) ||
            		connection.response().statusCode() == (201) ||
            		connection.response().statusCode() == (202) ||
            		connection.response().statusCode() == (203) ||
            		connection.response().statusCode() == (204) ||
            		connection.response().statusCode() == (205) ||
            		connection.response().statusCode() == (206) ||
            		connection.response().statusCode() == (207) ||
            		connection.response().statusCode() == (208) ||
            		connection.response().statusCode() == (226)){
            	UrlsStack.connectedHTTPRequests = UrlsStack.connectedHTTPRequests + 1;
            }
            else {
            	logger.info("Failed to connect " + url);
            	UrlsStack.failedHTTPRequests = UrlsStack.failedHTTPRequests + 1;
            	return false;
            }
            if(!connection.response().contentType().contains("text/html")){
                logger.info("Failure!! Retrieved something other than HTML");
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            for(Element link : linksOnPage)
            {
                this.links.add(link.absUrl("href"));
            }
            return true;
        }
        catch(IOException ioe){
        	UrlsStack.failedHTTPRequests = UrlsStack.failedHTTPRequests + 1;
        	logger.error("Failed in visiting " + url);
            return false;
        }
    }
    public List<String> getLinks()
    {
        return this.links;
    }
}
