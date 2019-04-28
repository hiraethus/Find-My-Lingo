#!/bin/bash

yum install -y \
    httpd \
    java-1.8.0-openjdk \
    tomcat \
    postgresql-server \
    postgresql-contrib

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

mkdir -p /var/log/findmylingo/

systemctl enable httpd

#--- PostgreSQL - see https://fedoraproject.org/wiki/PostgreSQL#Installation

# initialize the db
sudo postgresql-setup initdb

#--- allow password auth TODO: mount all of these files- don't copy them
cp /conf/postgresql/pg_hba.conf /var/lib/pgsql/data/pg_hba.conf

# enable and start
sudo systemctl enable postgresql
sudo systemctl start postgresql

sudo -i -u postgres psql -c "CREATE USER ${PG_USER} WITH PASSWORD '${PG_PASS}';"
sudo -i -u postgres psql -c "CREATE DATABASE ${PG_DB} OWNER ${PG_USER};"

# TODO: firewalld configuration - open port 80, block everything else

sudo cp -R /opt/findmylingo-webapp/ /var/lib/tomcat/webapps
sudo chown -R root:tomcat /usr/share/tomcat/webapps/

# DO manually:
# systemctl start tomcat
# systemctl start httpd
