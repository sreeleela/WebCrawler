package com.project.urlmanager;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.project.webcrawler.Crawler;

import org.powermock.core.classloader.annotations.PrepareForTest;


@RunWith(PowerMockRunner.class)
@PrepareForTest({Jsoup.class})
public class UrlsStackTest
{	
	Crawler crawler;
	URL url;
	
	@Before
    public void setup() throws MalformedURLException {
		crawler = Mockito.mock(Crawler.class);
		url = new URL("http://www.testurl.com");
	}
	
	@After
    public void clear() {
		UrlsStack.pagesToVisit.clear();
		UrlsStack.pagesVisited.clear();
		UrlsStack.totalHTTPRequests = 0;
		UrlsStack.failedHTTPRequests = 0;
		UrlsStack.connectedHTTPRequests = 0;
		UrlsStack.start = true;
	}
	
    @Test
    public void pagesToVisitEmpty() throws IOException{
    	String url = "";
    	UrlsStack.search(url);
    	assertEquals(UrlsStack.totalHTTPRequests, 0);
    }
	
    @Test
    public void pagesToVisitNotEmpty() throws IOException{
    	UrlsStack.pagesToVisit.add(url.toString());
    	Mockito.when(crawler.crawl(Mockito.anyString())).thenCallRealMethod();
    	UrlsStack.search(url.toString());
    	assertEquals(UrlsStack.totalHTTPRequests, 1);
    }
	
    @Test
    public void noCircularLoop() throws IOException{
    	UrlsStack.pagesToVisit.add(url.toString());
    	UrlsStack.pagesToVisit.add(url.toString());
    	Mockito.when(crawler.crawl(Mockito.anyString())).thenCallRealMethod();
    	UrlsStack.search(url.toString());
    	assertEquals(UrlsStack.totalHTTPRequests, 1);
    }
	
    @Test
    public void lastPageVisited() throws IOException{
    	UrlsStack.pagesToVisit.add(url.toString());
    	URL urlNotRepeated = new URL("http://www.notrepeated.com");
    	UrlsStack.pagesToVisit.add(urlNotRepeated.toString());
    	UrlsStack.pagesToVisit.add(url.toString());
    	Mockito.when(crawler.crawl(Mockito.anyString())).thenCallRealMethod();
    	UrlsStack.search(url.toString());
    	assertEquals(UrlsStack.totalHTTPRequests, 2);
    }
    
    @Test
    public void lastPageNotVisited() throws IOException{
    	UrlsStack.pagesToVisit.add(url.toString());
    	URL urlNotRepeated = new URL("http://www.notrepeated.com");
    	UrlsStack.pagesToVisit.add(urlNotRepeated.toString());
    	UrlsStack.pagesToVisit.add(url.toString());
    	URL urlNotRepeatedTwo = new URL("http://www.notrepeatedTwo.com");
    	UrlsStack.pagesToVisit.add(urlNotRepeatedTwo.toString());
    	Mockito.when(crawler.crawl(Mockito.anyString())).thenCallRealMethod();
    	UrlsStack.search(url.toString());
    	assertEquals(UrlsStack.totalHTTPRequests, 3);
    }
    
    @Test
    public void lastPagesVisited() throws IOException{
    	UrlsStack.pagesToVisit.add(url.toString());
    	URL urlNotRepeated = new URL("http://www.notrepeated.com");
    	UrlsStack.pagesToVisit.add(urlNotRepeated.toString());
    	UrlsStack.pagesToVisit.add(url.toString());
    	URL urlNotRepeatedTwo = new URL("http://www.notrepeatedTwo.com");
    	UrlsStack.pagesToVisit.add(urlNotRepeatedTwo.toString());
    	UrlsStack.pagesToVisit.add(url.toString());
    	UrlsStack.pagesToVisit.add(url.toString());
    	UrlsStack.pagesToVisit.add(url.toString());
    	UrlsStack.pagesToVisit.add(url.toString());
    	Mockito.when(crawler.crawl(Mockito.anyString())).thenCallRealMethod();
    	UrlsStack.search(url.toString());
    	assertEquals(UrlsStack.totalHTTPRequests, 3);
    }
    
    @Test
    public void lastPagesNotVisited() throws IOException{
    	UrlsStack.pagesToVisit.add(url.toString());
    	UrlsStack.pagesToVisit.add(url.toString());
    	UrlsStack.pagesToVisit.add(url.toString());
    	UrlsStack.pagesToVisit.add(url.toString());
    	URL urlNotRepeated = new URL("http://www.notrepeated.com");
    	UrlsStack.pagesToVisit.add(urlNotRepeated.toString());
    	URL urlNotRepeatedTwo = new URL("http://www.notrepeatedTwo.com");
    	UrlsStack.pagesToVisit.add(urlNotRepeatedTwo.toString());
    	Mockito.when(crawler.crawl(Mockito.anyString())).thenCallRealMethod();
    	UrlsStack.search(url.toString());
    	assertEquals(UrlsStack.totalHTTPRequests, 3);
    }
    
}
