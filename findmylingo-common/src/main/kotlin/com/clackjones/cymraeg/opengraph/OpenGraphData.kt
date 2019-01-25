package com.clackjones.cymraeg.opengraph

class OpenGraphData {
    val url: String?
    val title: String?
    val type: String?
    val description: String?
    val image: String?

    constructor(url: String?, title: String?, type: String?, description: String?, image: String?) {
        this.url = url
        this.title = title
        this.type = type
        this.description = description
        this.image = image
    }
}
