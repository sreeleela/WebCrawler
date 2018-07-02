package com.project.webcrawler;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import com.project.urlmanager.UrlsStack;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;


@RunWith(PowerMockRunner.class)
@PrepareForTest({Jsoup.class})
public class CrawlerTest
{
	Document htmlDocument;
    Connection jsoupConnect;
    Connection connection;
    Response response;
    Elements elements;
    UrlsStack urlsStack;
    String SITE_URL;
    
	@Before
    public void setup() {
		PowerMockito.mockStatic(Jsoup.class);
		htmlDocument = Mockito.mock(Document.class);
        jsoupConnect = Mockito.mock(Connection.class);
        connection = Mockito.mock(Connection.class);
        response = Mockito.mock(Response.class);
        urlsStack = Mockito.mock(UrlsStack.class);
        elements = new Elements();
        
        PowerMockito.when(Jsoup.connect(Mockito.anyString())).thenReturn(jsoupConnect);
        PowerMockito.when(jsoupConnect.userAgent(Mockito.anyString())).thenReturn(connection);
	}
   
    @Test
    public void allGood() throws IOException{
        SITE_URL = "successUrl";
        Mockito.when(connection.get()).thenReturn(htmlDocument);
        Mockito.when(connection.response()).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(200);
        Mockito.when(response.contentType()).thenReturn("text/html");
        Mockito.when(htmlDocument.select(Mockito.anyString())).thenReturn(elements);
        Crawler crawler = new Crawler();
        boolean result = crawler.crawl(SITE_URL);
        assertEquals(result, true);
    }
    
    @Test
    public void connectionFailure() throws IOException{
        SITE_URL = "brokenUrl";
        Mockito.when(connection.get()).thenThrow(new IOException());
        Crawler crawler = new Crawler();
        crawler.crawl(SITE_URL);
        boolean result = crawler.crawl(SITE_URL);
        assertEquals(result, false);
    }
    
    @Test
    public void status400() throws IOException{
        SITE_URL = "brokenUrl";
        Mockito.when(connection.get()).thenReturn(htmlDocument);
        Mockito.when(connection.response()).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(400);
        Crawler crawler = new Crawler();
        boolean result = crawler.crawl(SITE_URL);
        assertEquals(result, false);
    }
    
    @Test
    public void status404() throws IOException{
        SITE_URL = "brokenUrl";
        Mockito.when(connection.get()).thenReturn(htmlDocument);
        Mockito.when(connection.response()).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(404);
        Crawler crawler = new Crawler();
        boolean result = crawler.crawl(SITE_URL);
        assertEquals(result, false);
    }
    
    @Test
    public void status500() throws IOException{
        SITE_URL = "brokenUrl";
        Mockito.when(connection.get()).thenReturn(htmlDocument);
        Mockito.when(connection.response()).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(500);
        Crawler crawler = new Crawler();
        boolean result = crawler.crawl(SITE_URL);
        assertEquals(result, false);
    }
    
    @Test
    public void status502() throws IOException{
        SITE_URL = "brokenUrl";
        Mockito.when(connection.get()).thenReturn(htmlDocument);
        Mockito.when(connection.response()).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(502);
        Crawler crawler = new Crawler();
        boolean result = crawler.crawl(SITE_URL);
        assertEquals(result, false);
    }
    
    @Test
    public void status204() throws IOException{
        SITE_URL = "successUrl";
        Mockito.when(connection.get()).thenReturn(htmlDocument);
        Mockito.when(connection.response()).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(202);
        Mockito.when(response.contentType()).thenReturn("text/html");
        Mockito.when(htmlDocument.select(Mockito.anyString())).thenReturn(elements);
        Crawler crawler = new Crawler();
        boolean result = crawler.crawl(SITE_URL);
        assertEquals(result, true);
    }
    
    @Test
    public void noHtmlReturned() throws IOException{
        SITE_URL = "successUrl";
        Mockito.when(connection.get()).thenReturn(htmlDocument);
        Mockito.when(connection.response()).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(200);
        Mockito.when(response.contentType()).thenReturn("not-texthtml");
        Mockito.when(htmlDocument.select(Mockito.anyString())).thenReturn(elements);
        Crawler crawler = new Crawler();
        boolean result = crawler.crawl(SITE_URL);
        assertEquals(result, false);
    }
}
