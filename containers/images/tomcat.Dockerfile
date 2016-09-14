# Build a simple MySQL data volume Docker container.
FROM tomcat:8.0

# Install MySQL's client

RUN apt-get update && apt-get -y install mysql-client

# Configure user and group

RUN mv /root/.bashrc /etc/bashrc
COPY ./config/.bashrc /root/.bashrc

# Configure Tomcat

WORKDIR /usr/local/tomcat/webapps

EXPOSE 8080

CMD ["catalina.sh", "run"]
