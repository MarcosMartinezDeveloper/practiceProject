practice_projectpractice_projectpractice_projectpractice_project
# PracticeProject

## View the project in the browser

URL: [http://localhost:1234/](http://localhost:1234/)

## LAMP

I am using a LAMP stack as an easy way to set up a MySQL server.

### Installing and configuring the LAMP stack using bash

```
sudo apt update
sudo apt upgrade
sudo apt install apache2
sudo apt install mysql-server

sudo mysql
	CREATE DATABASE practice_project;
	CREATE USER 'practice_user'@'localhost' IDENTIFIED BY 'abcD123.';
	GRANT ALL PRIVILEGES ON practice_project.* TO 'practice_user'@'localhost';
	FLUSH PRIVILEGES;
exit
```
	
*Starting the LAMP*

``` bash
sudo systemctl start apache2 mysql
```

*Stoping the LAMP*

```bash
sudo systemctl stop apache2 mysql
```

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.2.0-SNAPSHOT/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.2.0-SNAPSHOT/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.2.0-SNAPSHOT/reference/web/servlet.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/4.2.0-SNAPSHOT/reference/using/devtools.html)
* [Thymeleaf](https://docs.spring.io/spring-boot/4.2.0-SNAPSHOT/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)
* [Spring Security](https://docs.spring.io/spring-boot/4.2.0-SNAPSHOT/reference/web/spring-security.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/4.2.0-SNAPSHOT/reference/data/sql.html#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
