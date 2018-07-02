package com.project.urlmanager;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.project.webcrawler.Crawler;

public class UrlsStack {
	
	static Logger logger = LoggerFactory.getLogger(UrlsStack.class);
    public static Set<String> pagesVisited = new HashSet<String>();
    public static List<String> pagesToVisit = new LinkedList<String>();
    public static boolean start = true;
    public static int totalHTTPRequests = 0;
    public static int failedHTTPRequests = 0;
    public static int connectedHTTPRequests = 0;
	
    public static Set<String> getPagesVisited() {
		return pagesVisited;
	}
	public static void setPagesVisited(Set<String> pagesVisited) {
		UrlsStack.pagesVisited = pagesVisited;
	}
	public static List<String> getPagesToVisit() {
		return pagesToVisit;
	}
	public static void setPagesToVisit(List<String> pagesToVisit) {
		UrlsStack.pagesToVisit = pagesToVisit;
	}
	
	public static String nextUrl()
    {
        String nextUrl = "";
        do
        {
        	if(pagesToVisit.size()>0)
        		nextUrl = pagesToVisit.remove(0);
        } while(pagesVisited.contains(nextUrl) && pagesToVisit.size()>0);
        if(pagesVisited.contains(nextUrl) && pagesToVisit.size()==0) {
        	return "";
        }
        else {
        	pagesVisited.add(nextUrl);
        	return nextUrl;
        }
    }
	
	public static void search(String url)
	  {
	      while(pagesToVisit.size() > 0)
	      {
	          String currentUrl;
	          Crawler  crawler= new Crawler();
	          if(start)
	          {
	              currentUrl = url;
	              pagesVisited.add(url);
	              start = false;
	          }
	          else
	          {
	              currentUrl = nextUrl();
	          }
	          if(!(currentUrl.equals(""))) {
	        	  boolean result = crawler.crawl(currentUrl);
	        	  pagesToVisit.addAll(crawler.getLinks());
	          }
	      }
	      logger.info("Total HTTP Requests: "+ totalHTTPRequests);
	      logger.info("Connected HTTP Requests: "+ connectedHTTPRequests);
	      logger.info("Failed HTTP Requests: "+ failedHTTPRequests);   
	  }
}
