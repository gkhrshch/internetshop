# Internet-shop

# Table of Contents
* [Project description](#description)
* [Technologies applied](#technologies)
* [How to run the project](#launch)

# <a name="description"></a>Project description
Internet shop template training project 

Actions available:
- check the list of available products
User functional:
- add products to bucket 
- create orders from bucket
- view created orders
Admin functional: 
- view users from db

# <a name="technologies"></a>Technologies used
* Java 12
* Maven 4.0.0
* MavenCheckstyle 3.1.0
* Tomcat 8.5.45
* MySQL 8.0.17
* Hibernate 5.4.5
* Log4j 1.2.17
* Servlet 3.1.0
* JSTL 1.2

# <a name="launch"></a>How to run the project?

1. Clone or download the project from github.com
2. Add to IDE as maven project
3. Add tomcat configuration
4. Add artifact internetshop:war exploded
5. Setup and configure your database management system
6. Copy and execute init_db.sql file in your database console
7. Change login and password in Factory and hibernate.cfg.xml 
8. Configure path in log4j.properties

Activate JDBC implementation:
1. Change respective DaoImpl classes in Factory
2. Add @Dao annotations over JdbcImpl classes and remove them over HibernateImpl classess