# WebCrawler
This is a java project developed with maven. (java 8 recommended to run this project). Maven wrapper is used. 

1. Crawler hits endpoint and then proceeds to navigate to each link.
2. The application detects circular links to prevent an infinite loop.
3. Crawl all the links (below) and print out summary statistics at the end:
     * Total number of http requests performed throughout the entire application
     * Total number of successful requests
     * Total number of failed requests
Links: https://raw.githubusercontent.com/OnAssignment/compass-interview/master/data.json
     
# Build and Run
1. Download project
2. Open terminal/command prompt 
3. Navigate to the folder where you downloaded the project 
4. Execute following commands
     * mvnw.cmd clean install (windows) [or] ./mvnw clean install (linux)
     * java -jar target\spider-0.0.1-SNAPSHOT.jar
