# Currency Vat MS

## Describe

This Spring boot application has get 3 endpoint:<br/>
  1 - "The current time" service is running in the base directory<br/>
  2 - "Convert currency" service is running "/api/currency/convert" directory<br/>
  3 - "Validate a VAT input and return" service is running in the base directory (but this endpoint get "potential VAT number" input)<br/>
  <br/>
  <b>I used in memory DB in this application. For save all request incoming to endpoint. (H2 DB) <b>
  
  The service includes auto generate swagger documentation (You can see the below this link):
  ```bash
http://localhost:8081/swagger-ui.html#/
```
This application were dockerize. (You can see Dockerfile in the repository files) And this application's has a docker-compose.yml

## Installation / Running
You should run the below code in the project base directory 
```bash
docker-compose up
```

## Unit-Test

the application has unit-tests. If you make build to this application, all tests will run

Or you can use below command in the project base directory for running all test

  ```bash
mvn test
```
