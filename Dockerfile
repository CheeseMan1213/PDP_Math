# Pulls the base container image for OpenJDK 17 from Docker hub.
#FROM openjdk:17-jdk
FROM openjdk:17-alpine
# Places the most recently built JAR file into the container environment.
COPY ./build/libs/PDP_Math-1.0.0.jar PDP_Math-1.0.0.jar
# Exposes port 8080. This is important. It must match the port stated in the application.properties file.
EXPOSE 8080/tcp
# Used the command 'CMD' to run the app on container start up.
# Remember:
# RUN = Executes shell commands during the process of building the image.
# These commands do not run on container start up, only for the image build.
# ENTRYPOINT = Sets commands to run on container startup that you cannot overwright.
# CMD = Sets commands to run on container startup that may be overwrighten.
CMD ["java", "-jar", "PDP_Math-1.0.0.jar"]

# podman run -p 8080:8080 -d --name pdp-math pdp-math

