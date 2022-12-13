# Url-Shortener

Users can enter a URL on a page and get back a shortened version. The shortened version of a URL is the same for every shortening request.
If this shortened URL is used as an address in a browser, the document of the unabridged URL is displayed after being redirected via the Internet
service.

### How To Run
1. The first prerequisite is to have Java installed on your system and have a standard IDE such as IntelliJ.
2. The Second is to have Mysql WorkBench, as we are Mysql to persist data for this project.
3. Clone this project and open it on your IDE.
4. Ensure that all maven dependencies install successfully
5. Ensure you have the correct credentials for your mysql connection
6. You could use the play button or run the project with:
   ```sh
   mvn spring-boot:run
   ```
7. Launch your browser and go to the index url:
   http://localhost:8080/shortener
8. If you have an account or if you want to register you can go to:
   http://localhost:8080/menu

Kindly Note: That you need to have your application running on  localhost before testing the short urls as we are currently not using an external domain for this application