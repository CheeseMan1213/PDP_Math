# Stage 1: Download the OpenTelemetry Java agent.
FROM curlimages/curl:8.2.1 AS download
ARG OTEL_AGENT_VERSION="1.29.0"
RUN curl --silent --fail -L "https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v${OTEL_AGENT_VERSION}/opentelemetry-javaagent.jar" \
    -o "$HOME/opentelemetry-javaagent.jar"

# Stage 2: Build the application.
FROM gradle:7.5.1-jdk17 AS build

WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src src
# I had a bit of trouble getting checks starting to run so I decided to skip all the checks for the build..
# You are expected to run these checks one testing locally.
# You are expected to run these checks explicitly in the pipeline first, then build the image without them.
RUN gradle build --no-daemon -x test -x checkstyleMain -x checkstyleTest -x spotbugsMain -x spotbugsTest


# Stage 3: Run the application.
FROM alpine:latest

ENV JAVA_VERSION=17
ENV JAVA_PACKAGE=openjdk${JAVA_VERSION}
# Update the base image.
RUN apk update
RUN apk upgrade
RUN apk add --no-cache ${JAVA_PACKAGE}
ENV JAVA_HOME=/usr/lib/jvm/${JAVA_PACKAGE}
ENV PATH=${PATH}:${JAVA_HOME}/bin

# Create and change to non-root user.
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=build /app/build/libs/PDP_Math-1.0.0.jar /app/PDP_Math-1.0.0.jar
COPY --from=download /home/curl_user/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

# Places the most recently built JAR file into the container environment.
# COPY ./build/libs/PDP_Math-1.0.0.jar PDP_Math-1.0.0.jar
# Exposes port 8080. This is important. It must match the port stated in the application.properties file.
EXPOSE 8080/tcp

# Places the container into read only mode. This is more secure, but it also means that
# the application cannot write to the container.
# It might cause problems.
ENV READ_ONLY=true

# Used the command 'CMD' to run the app on container start up.
# Remember:
# RUN = Executes shell commands during the process of building the image.
# These commands do not run on container start up, only for the image build.
# ENTRYPOINT = Sets commands to run on container startup that you cannot overwright.
# CMD = Sets commands to run on container startup that may be overwrighten.
# CMD ["java", "-jar", "/app/PDP_Math-1.0.0.jar"]
ENTRYPOINT ["java", "-javaagent:/opentelemetry-javaagent.jar", "-jar", "/app/PDP_Math-1.0.0.jar"]

# podman run -p 8080:8080 -d --name pdp-math pdp-math
