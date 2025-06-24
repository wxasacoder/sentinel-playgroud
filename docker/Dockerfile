FROM openjdk:8
WORKDIR /usr/sentinel
ADD ./sentinel-dashboard-1.8.8.jar sentinel-dashboard-1.8.8.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "sentinel-dashboard-1.8.8.jar", "--server.port=8088"]