 # WebCrawler
This is a java project developed with maven. (java 8 recommended to run this project). Maven wrapper is used. 

1. Crawler hits endpoint and then proceeds to navigate to each link.
2. The application detects circular links to prevent an infinite loop.
3. Crawl all the links (below) and print out summary statistics at the end:
     * Total number of http requests performed throughout the entire application
     * Total number of successful requests
     * Total number of failed requests

<br>Links: https://raw.githubusercontent.com/OnAssignment/compass-interview/master/data.json
     
# Build and Run
1. Download project
2. Open terminal/command prompt 
3. Navigate to the folder where you downloaded the project 
4. Execute following commands
     * mvnw.cmd clean install (windows) [or] ./mvnw clean install (linux)
     * java -jar target\spider-0.0.1-SNAPSHOT.jar

# JSON Data
{
	"links": [
		"https://httpbin.org/status/200",
		"https://httpbin.org/links/1",
		"https://httpbin.org/links/10",
		"https://httpbin.org/status/500",
		"https://httpbin.org/links/2",
		"https://httpbin.org/links/98",
		"https://httpbin.org/status/404",
		"https://httpbin.org/links/7",
		"https://httpbin.org/status/400",
		"https://httpbin.org/links/4",
		"https://httpbin.org/links/10",
		"https://httpbin.org/status/502",
		"https://httpbin.org/status/404",
		"https://httpbin.org/links/7",
		"https://httpbin.org/status/400",
		"https://httpbin.org/links/4",
		"https://httpbin.org/links/10",
		"https://httpbin.org/status/502",
		"https://httpbin.org/links/2",
		"https://httpbin.org/links/1",
		"https://httpbin.org/links/0",
		"https://httpbin.org/status/404",
		"https://httpbin.org/links/7",
		"https://httpbin.org/status/400",
		"https://httpbin.org/links/4",
		"https://httpbin.org/links/10",
		"https://httpbin.org/status/502",
		"https://httpbin.org/links/2",
		"https://httpbin.org/links/1",
		"https://httpbin.org/status/404",
		"https://httpbin.org/links/7",
		"https://httpbin.org/status/400",
		"https://httpbin.org/links/4",
		"https://httpbin.org/links/10",
		"https://httpbin.org/status/404",
		"https://httpbin.org/links/7",
		"https://httpbin.org/status/400",
		"https://httpbin.org/links/4",
		"https://httpbin.org/links/10",
		"https://httpbin.org/status/502",
		"https://httpbin.org/status/502",
		"https://httpbin.org/links/0",
		"https://httpbin.org/status/404",
		"https://httpbin.org/links/7",
		"https://httpbin.org/status/400",
		"https://httpbin.org/links/4",
		"https://httpbin.org/links/10",
		"https://httpbin.org/status/502",
		"https://httpbin.org/status/404",
		"https://httpbin.org/links/7",
		"https://httpbin.org/status/400",
		"https://httpbin.org/links/4",
		"https://httpbin.org/links/10",
		"https://httpbin.org/status/502",
		"https://httpbin.org/links/2",
		"https://httpbin.org/links/1",
		"https://httpbin.org/links/0",
		"https://httpbin.org/status/200",
		"https://httpbin.org/links/54",
		"https://httpbin.org/status/200",
		"https://httpbin.org/links/3",
		"https://httpbin.org/links/7"
	]
}
