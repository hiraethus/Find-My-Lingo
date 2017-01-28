## Vagrant  and Virtualbox Installation

If you are on MS Windows, use [chocolatey](https://chocolatey.org) package manager
to install vagrant and virtualbox on your development machine(remember to run as _administrator_):

*Note* I chose vagrant version 1.8.7 due to an issue with 
interoperability with berkshelf in Windows 10. This may be 
fixed by the time you read this.

Also, ensure that your version of virtualbox that is installed is `5.1`
or higher. This was required in order for the Ubuntu 16.04 virtualbox
image to work successfully.

```
choco install vagrant --version 1.8.7
choco install virtualbox
```

You may need to restart your computer.

## Install the ChefDK
In MS Windows on your dev machine, this can be achieved by using the chocolatey
package manager (remember to run as _administrator_):

```
choco install chefdk
```

As part of this setup, `vagrant` is going to need to use the 
`berkshelf` plugin in order to manage chef cookbooks:

```
vagrant plugin install vagrant-berkshelf
```

## Run vagrant
Ensure you are in this directory and type `vagrant up`

## Deploy your code
Find out the IP address of your virtual machine by performing `vagrant ssh` and
using ifconfig (e.g. `192.168.1.33`)

Exit the virtual machine and run this command from the current directory:

```
mvn tomcat7:redeploy -Dmaven.tomcat.url=http://<vagrant-ip-addr>:8080/manager/text -Dmaven.tomcat.path=/gc -Dtomcat.username=mavenDeploy -Dtomcat.password=mavenDeployPwd
```

And visit http://vagrant-ip-addr:8080/gc in your browser.

Every time you update your code, run the command above to redeploy changes.