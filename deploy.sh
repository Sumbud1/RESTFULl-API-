#!/bin/bash

mvn clean package
sudo systemctl stop tomcat
sudo rm -rf /opt/tomcat/webapps/Gestion_UE_VF-1.0-SNAPSHOT*
sudo cp target/Gestion_UE_VF-1.0-SNAPSHOT.war /opt/tomcat/webapps/
sudo chown tomcat:tomcat /opt/tomcat/webapps/*.war
sudo systemctl start tomcat
