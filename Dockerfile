FROM openjdk:17
EXPOSE 8080
ADD /target/fierce_back_prod.jar fierce_back_prod.jar
ENTRYPOINT ["java" , "-jar", "-Dspring.profiles.active=prod" , "fierce_back_prod.jar"]