FROM eclipse-temurin:22
RUN mkdir /opt/app
COPY mealideas.jar /opt/app
CMD ["java", "-jar", "/opt/app/mealideas.jar"]