FROM eclipse-temurin:19-jdk
COPY . .
RUN sed -i '/JAVA_HOME/d' build.sh && chmod +x /start.sh && chmod +x build.sh && chmod +x gradlew
VOLUME ["/tmp/db"]
EXPOSE 80
ENTRYPOINT ["/start.sh"]
