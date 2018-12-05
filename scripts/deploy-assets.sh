#!/bin/bash

# TODO mount assets etc. in Vagrant instead of running copy command
vagrant ssh -c 'sudo cp -R /findmylingo/static/* /var/www/findmylingo.local/static'
