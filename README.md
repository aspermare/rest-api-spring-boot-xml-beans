# rest-api-spring-boot-xml-beans
Sample of creating REST API with Spring-boot, JPA, and SQLite db.

This sample implements two REST APIs:
 1) POSTing number into SQLite db table.
 2) GETting/calculating aggregation (Sum, Avg, Min, Max) based on stored numbers.
    Aggregation operations are implemented as spring beans initiated from xml configuration file.
