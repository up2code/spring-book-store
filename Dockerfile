FROM amazoncorretto:21-alpine as corretto-jdk

# required for strip-debug to work
RUN apk add --no-cache binutils

# Build small JRE image
RUN jlink \
         --add-modules ALL-MODULE-PATH \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /jre

FROM alpine:latest
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY --from=corretto-jdk /jre $JAVA_HOME

EXPOSE 8080

ADD target/*.jar /app.jar
RUN mkdir /opt/app

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]