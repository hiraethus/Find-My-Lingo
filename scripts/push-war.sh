#!/bin/bash

# Run to deploy ROOT.war to Vagrant
vagrant ssh -c 'sudo cp  /findmylingo/target/ROOT.war /var/lib/tomcat/webapps/'