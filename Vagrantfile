# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  config.vm.box = "bento/ubuntu-16.04"
  config.vm.network "private_network", type: "dhcp"

  config.vm.provider "virtualbox" do |vb|
    vb.memory = "1024"
  end

  config.berkshelf.enabled = true
  config.berkshelf.berksfile_path = "./cookbooks/dev/Berksfile"

  config.vm.provision "chef_solo" do |chef|
    chef.run_list = [
        'recipe[dev]'
    ]
  end
end
