# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
   # Fix for vb-guest issue
   # https://github.com/dotless-de/vagrant-vbguest/issues/333
   if Vagrant.has_plugin?("vagrant-vbguest")
      config.vbguest.auto_update = false
   end

  config.vm.box = "bento/fedora-28"

  config.vm.synced_folder "./findmylingo-webapp", "/findmylingo"
  config.vm.synced_folder "./findmylingo-webapp/target/webapps", "/opt/findmylingo-webapp"
  config.vm.synced_folder "./findmylingo-admintools/target/", "/opt/findmylingo/tools"
  config.vm.synced_folder "./conf", "/conf"

  config.vm.network "private_network", ip: "192.168.33.10"

   config.vm.provider "virtualbox" do |vb|
     vb.memory = "4096"
   end

  config.vm.provision "shell", path: "./scripts/vagrant-provision.sh", env: {
    "PG_USER" => "findmylingo",
    "PG_PASS" => "findmylingo",
    "PG_DB"   => "findmylingo"
  }
end

# TODO: database not connecting - fix issue
# SET LOG level to DEBUG and restart
