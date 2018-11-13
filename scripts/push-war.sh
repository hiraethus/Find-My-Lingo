#!/bin/bash

# Run to deploy ROOT.war to Vagrant
vagrant ssh -c 'sudo cp  /findmylingo/target/ROOT.war /var/lib/tomcat/webapps/'
vagrant ssh -c 'sudo systemctl stop tomcat'
vagrant ssh -c 'sudo systemctl start tomcat'