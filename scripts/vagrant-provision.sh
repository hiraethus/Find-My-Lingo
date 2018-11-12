#!/bin/bash

dnf upgrade -y

dnf install -y httpd
dnf install -y java-1.8.0-openjdk

dnf install -y tomcat

# add httpd configuration file
mkdir -p /var/www/findmylingo.local/static
cp -R /findmylingo/static/* /var/www/findmylingo.local/static

# copy findmylingo VirtualHosts config to httpd
cp /findmylingo/conf/find_my_lingo_httpd.conf /etc/httpd/conf.d/

# start tomcat and httpd
systemctl enable tomcat
systemctl start tomcat

systemctl enable httpd
systemctl start httpd

# TODO: firewalld configuration - open port 80, block everything else