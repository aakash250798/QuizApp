## QuizApp
Microsservice implementation of Quiz App that lets user create questions, quiz, get results and many moe stuffs


Its a backend application of a Quiz which lets user create any no of questions with some particular categories and diffiiculty levels.. also it lets user create a quiz based on the required choices of categories and difficluty, and lets user submit answers to quiz , lets us generate score based on the answers.
So, used microservice architecturre in this application with tools like Java, Spring Boot, PostgreSQL, API Gateway, Eureka Server, Eureka Client, OpenFeign, etc.





Clone the project

```bash
git clone -b master https://github.com/aakash250798/QuizApp.git

```





Install dependencies

```bash
mvn clean install -U
mvn eclipse:eclipse
mvn eclipse:clean
```

Go to the project directory

```bash
Make changes to the application.properties file in src/main/resources folder like- postgres-username, password, etc.
Also create a database and set the url for the database in properties file 
```


Start the server

```bash
  right click on the project name and select run as -> SpringBootApp and wait for the server to Start
  check the port and open localhost:port in ur browser
 Use postman to d the necessary stuffs of adding, viewing, deleting questions/quiz, submitting them and creating the results based on that
```
