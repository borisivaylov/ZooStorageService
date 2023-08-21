
FROM openjdk:17

WORKDIR /zoo-storage

COPY rest/target/ZooStorageServiceApplication.jar zoo-storage.jar

EXPOSE 8082

CMD ["java", "-jar", "zoo-storage.jar"]





