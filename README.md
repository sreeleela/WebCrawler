# WebCrawler
1. Crawler hits endpoint and then proceeds to navigate to each link.
2. The application detects circular links to prevent an infinite loop.
3. Crawl all the links (below) and print out summary statistics at the end:
     * Total number of http requests performed throughout the entire application
     * Total number of successful requests
     * Total number of failed requests
     
# Build and Run
1. Download zip file
2. Unzip it
3. Navigate to project folder
4. Execute following commands
     * mvnw.cmd clean install
     * java -jar target\spider-0.0.1-SNAPSHOT.jar
