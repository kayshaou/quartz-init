FROM tomcat:8.0-alpine
LABEL maintainer="deepak@softwareyoga.com"

ADD BasicQuartz-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8089

entrypoint ["/bin/bash", "/usr/local/tomcat/bin/catalina.sh", "run"]