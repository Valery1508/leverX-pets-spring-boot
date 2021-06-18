# LeverX Pets Service

# Task description:
1) Spring Boot
3) Hibernate
4) Validation API

# Database info:
- postgresql, h2
- one pet has one owner
- one owner may have many pets
- schema:
![image](https://user-images.githubusercontent.com/58391822/119480600-828e4400-bd5a-11eb-9b82-3a1f5f82d4ed.png)

# Postman link for testing:
https://www.getpostman.com/collections/905c0d4b291715293135

# Two profiles:
1) dev - postgres
2) prod - h2 database

To change profile, go to [application.properties](src/main/resources/application.properties) file and change field "spring.profiles.active".
