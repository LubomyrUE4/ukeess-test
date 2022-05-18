"# ukeess-test"


Conditions:
1. Table theoretically can have more fields and a lot of data - DONE
2. So, paging is essential part of this test task - DONE
(I didn't use PagingAndSortingRepository, 
but only used manual SQL queries for implementing pagination)
3. Please use MySQL for test task - DONE
4. Don’t use Hibernate or any other ORM - DONE (I used JDBCTemplate)
5. Please configure swagger - DONE
(You can access it with http://localhost:8090/swagger-ui/)
6. Please write a small tutorial how to start a project and attach a database dump
   with test data - DONE
7. Please use for front-end part Angular2+ or React - DONE (I used React)


Tutorial:
To launch the project you should start 
Spring Boot (run "src/main/java/com/example/ukeesstest/UkeessTestApplication.java") 
and React applications(run command "npm start" in "src/main/webapp/reactjs"),
so now you can access the project with http://localhost:3000/.

Database scheme is in "src/main/resources/db/migration", it will migrate automatically.
Also, you need to set your MySQL credentials in "application.properties":
app.datasource.username=...
app.datasource.password=...
spring.flyway.user=...
spring.flyway.password=...

and have a database named "ukeess_test".