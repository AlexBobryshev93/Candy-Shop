# Candy Shop

## Tutorial
A learning project - Online shop application.

### Technologies used:
* Java 8
* Spring Framework (Core, Boot, MVC, Data, Security)
* PostgreSQL
* Hibernate (via Spring Data JPA)
* Thymeleaf
* Maven

### Steps to setup

**1. Clone the repository (alternatively you can just download it) using 
the following command:**

```bash
git clone https://github.com/AlexBobryshev93/Candy-Shop.git
```

**2. Install and configure PostgreSQL**

First, install PostgreSQL and create a database named `candy`. 
Then, open `src/main/resources/application.properties` file and change 
the spring datasource username and password to yours if you 
didn't use the default values during the installation. The default username is
`user1` and password - `pass` .

**3. Run the application**

Use the following command from the root directory of the project to run it:

```bash
mvn spring-boot:run
```

Also you may do the same from your IDE.

**4. Use the application as you wish**

Now you can access the application at http://localhost:8080/ in your browser
(port number can be changed in `src/main/resources/application.properties`).

The application will require authentication. Use username
`user1` and password `pass` as the credentials. This default account has
some amount of money and gives an opportunity to test the
full functionality of the shop-app.

Enjoy!

Note: In order to create other accounts or change the money balance you
should clean the database or drop and create it again, change the corresponding 
data in `dataLoader()` method of `CandyShopApplication` class and rerun the 
application. Alternatively, it can be done with SQL directly in the database 
(password encryption should be considered in this case, the encoder can be 
found in `WebAndSecurityConfig` class).

### Disclaimer
This application was developed for learning purposes and doesn't consider some
aspects of real online shops, among those are:

* Thread safety
* Safe calculations using numbers with floating point (0.1 issue)