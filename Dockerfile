From openjdk:8
copy ./target/spring-boot-mysql-0.0.1-SNAPSHOT.jar spring-boot-mysql-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","spring-boot-mysql-0.0.1-SNAPSHOT.jar"]