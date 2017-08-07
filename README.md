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

## Setup instructions
* To develop this application, use vagrant to provision a machine. See the vagrant_instructions.md
* Please see the [Installation Guide](//github.com/hiraethus/gwasanaethau-cymraeg/wiki/Installation Guide) in the wiki (work in progress)
* Create RESTful interface for Android App
* Create thick client in AngularJS

## Errors to Fix/To-Do:
* Apostrophes replaced with &amp;amp;#39; when user adds a service
* Zip code only accepts xxxx xxx format, must also accept xxx xxx
* Have email field filled in when adding service
* Send confirmation email after user registers
* Make "Gwasanaethau Cymru" at top left link to homepage
* When user selects English, keep every webpage in English until they choose Cymraeg. And vice versa.
* Add "Search Services" button to left of "Add a Service" button. When click the button, a search bar appears underneath the buttons and whatever the user searches filters out the selections under "Service List". Search bar has different tabs, one to search by name, one by type, one by location. Auto-complete.
* Little arrows next to Name, Category and City so that user can list things in alphabetical order. 
