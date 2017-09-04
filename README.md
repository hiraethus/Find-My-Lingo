# Gwasanaethau Cymru

[http://gwasanaethau.cymru/](http://gwasanaethau.cymru/)

Gwasanaethau Cymru is a side project to enable speakers of [Welsh](https://en.wikipedia.org/wiki/Welsh_language) 
to use Welsh-language services in their local area. I intend for this project to 
allow businesses and local authorities to create profiles for their own services 
and to allow people to rate the standard of the service provided to them 
through the medium of Welsh.

The intention of this project is to make this codebase generic and well 
documented such that other technically minded people might host their own 
portals for their respective minority languages.

## To run with Jetty:
- Ensure you have Maven 3 Installed
- To make sure the server can send email, create a small file in your home directory called application.properties with credentials for a gmail account:
```
mail.username=gmail_username
mail.password=p@sSw0RD
```
- Clone this repository
- cd into the repository and type `mvn jetty:run`
- Visit the web application on `localhost:8080` in your browser.

## Setup instructions
* Full installation instructions will be available at [Installation Guide](https://github.com/hiraethus/gwasanaethau-cymraeg/wiki/Installation%20Guide) in the wiki
