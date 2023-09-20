# TradingApplication
Code challenge from DeutchBank

* Implementation
    * Java 17
    * Spring Boot 3.1.3
    * MongoDB 7.0.1
    * Swagger
        * http://localhost:8080/swagger-ui/index.html#/
        * http://localhost:8080/v3/api-docs
    * Spring Security
* To run it
  * Make sure do you have Docker installed 
  * Go to the directory /TradingApplication
      * Run ./gradlew build
      * Run ./gradlew run
* Setup MongoDB project
   * Go to swagger http://localhost:8080/swagger-ui/index.html#/
   * Authorize using the API-Key `109353c6-6432-4acf-8e77-ef842f64a664`
   * Run the Rest API http://localhost:8080/swagger-ui/index.html#/signal-controller/createSignal, use in the body request the content from the file `init.json`
* Improvements
  * Change the api /signal-controller/createSignal, to upload a file
  * Implement cache mechanism, REDIS might be a good idea
  * Implement migration DB Tool, possible choices
    * Liquibase MongoDB
    * Mongock