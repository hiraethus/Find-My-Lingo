#
# Cookbook Name:: dev
# Recipe:: default
#
# Copyright (C) 2017 Mike Clack Jones
#
# All rights reserved - Do Not Redistribute
#

mysql2_chef_gem 'default' do
  action :install
end

mysql_service 'default' do
  version '5.7'
  data_dir '/data'
  initial_root_password 'Ch4ng3me'
  action [:create, :start]
end

mysql_connection_info = {
  :host     => '127.0.0.1',
  :username => 'root',
  :password => 'Ch4ng3me'
}

mysql_database 'gwasanaethau_db' do
  connection    mysql_connection_info
  action :create
end

# grant all privileges to gwasanaethau user
mysql_database_user 'gwasanaethau' do
  connection    mysql_connection_info
  password      'mysqlpwd'
  database_name 'gwasanaethau_db'
  host          '%'
  action        [:create, :grant]
end

# install openjdk 8
apt_package 'openjdk-8-jdk-headless'

# install and configure tomcat
apt_package 'tomcat8'
apt_package ' tomcat8-admin'

# add a manager-script user so we can
# deploy our app using mvn
cookbook_file '/etc/tomcat8/tomcat-users.xml' do
  source 'tomcat-users.xml'
  owner 'root'
  group 'tomcat8'
  mode '0640'
  action :create
end

# add mysql connector to tomcat
include_recipe 'maven'
maven 'mysql-connector-java' do
  group_id 'mysql'
  version  '5.1.40'
  dest     '/usr/share/tomcat8/lib/'
end

# configure jndi jdbc resource
cookbook_file '/etc/tomcat8/context.xml' do
  source 'tomcat-context.xml'
  owner 'root'
  group 'tomcat8'
  mode '0640'
  action :create
end

# and restart
service "tomcat8" do
  action :restart
end