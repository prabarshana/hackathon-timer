FROM tomcat:jdk8-corretto
VOLUME /tmp
ARG WAR_FILE
ADD ${WAR_FILE} /usr/local/tomcat/webapps/app.war
RUN sh -c 'touch /usr/local/tomcat/webapps/app.war'
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/tomcat/webapps/app.war" ]