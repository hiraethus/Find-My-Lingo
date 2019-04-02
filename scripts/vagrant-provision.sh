#!/bin/bash

dnf upgrade -y

dnf install -y httpd
dnf install -y java-1.8.0-openjdk

dnf install -y tomcat

# add httpd configuration file
mkdir -p /var/www/findmylingo.local/static
cp -R /findmylingo/static/* /var/www/findmylingo.local/static
mkdir -p /var/www/findmylingo.local/static/service/images
# TODO: find out how to make these permissions tighter
sudo chmod 777 /var/www/findmylingo.local/static/service/images

# copy findmylingo VirtualHosts config to httpd
cp /findmylingo/conf/find_my_lingo_httpd.conf /etc/httpd/conf.d/

# start tomcat and httpd
systemctl enable tomcat
systemctl start tomcat

systemctl enable httpd
systemctl start httpd

#--- PostgreSQL - see https://fedoraproject.org/wiki/PostgreSQL#Installation
sudo dnf install -y postgresql-server postgresql-contrib


# install and setup ident server
sudo dnf install -y oidentd
sudo systemctl enable oidentd
sudo systemctl start  oidentd

# initialize the db
sudo postgresql-setup --initdb --unit postgresql

# enable and start
sudo systemctl enable postgresql
sudo systemctl start postgresql

sudo -i -u postgres createuser tomcat
sudo -i -u postgres createdb   tomcat

# TODO: firewalld configuration - open port 80, block everything else