FROM maven:3.8.5-openjdk-17 AS build

COPY . .
RUN mvn clean package -DskipTests
FROM tomcat:9.0-jdk17-openjdk-slim
COPY --from=build /target/newsapp-1.0.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 80
RUN sed -i 's/port="8080"/port="80"/' /usr/local/tomcat/conf/server.xml
CMD ["catalina.sh", "run"]
