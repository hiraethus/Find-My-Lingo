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

## Heroku setup instructions
- Set-up postgres on Heroku
```
heroku addons:create heroku-postgresql
```

- Add config vars for your gmail username and password that you wish to send mail from:
    - MAIL_USER
    - MAIL_PWD

## Run locally (Connect to Live Database)
- put `DATABASE_URL` in your CLI environment variables
```bash
export DATABASE_URL=$(heroku config:get DATABASE_URL)
```

- Run the java command
```bash
java -jar target/dependency/webapp-runner.jar target/*.war
```

## Setup instructions
* Full installation instructions will be available at [Installation Guide](https://github.com/hiraethus/gwasanaethau-cymraeg/wiki/Installation%20Guide) in the wiki
